package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.data.factory.CoinsDataFactory
import com.marko.domain.time.now
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsCacheDataSourceTest {

	private val coinsCacheRepository = mockk<CoinsCacheRepository>()
	private val coinsCacheDataSource = CoinsCacheDataSource(coinsCacheRepository)

	@Test
	fun `does cache data source isCacheValid calls repository`() {
		stubIsCacheValid(true)

		coinsCacheDataSource.isCacheValid

		verify(exactly = 1) { coinsCacheRepository.isCacheValid }
	}

	@Test
	fun `does cache data source lastCacheTime delegates to repository`() {
		val lastCacheTime = now
		stubLastCacheTime(lastCacheTime)


		assert(coinsCacheDataSource.lastCacheTime == lastCacheTime)

		val newCacheTime = now

		coinsCacheRepository.lastCacheTime = newCacheTime

		verify(exactly = 1) { coinsCacheRepository.lastCacheTime }
		verify(exactly = 1) { coinsCacheRepository.lastCacheTime = newCacheTime }
	}

	@Test
	fun `does cache data source getCoins calls repository`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		coinsCacheDataSource.getCoins()

		coVerify(exactly = 1) { coinsCacheRepository.getCoins() }
	}

	@Test
	fun `check cache data source getCoins result`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		val result = coinsCacheDataSource.getCoins()

		assert(result == coins)
	}

	@Test
	fun `does cache data source getCoin calls repository`() = runBlocking {
		val coin = CoinsDataFactory.coinData
		stubCoin(coin)

		val coinId = 1

		coinsCacheDataSource.getCoin(coinId)

		coVerify(exactly = 1) { coinsCacheRepository.getCoin(coinId) }
	}

	@Test
	fun `check cache data source getCoin result`() = runBlocking {
		val coin = CoinsDataFactory.coinData
		stubCoin(coin)

		val coinId = 1

		val result = coinsCacheDataSource.getCoin(coinId)

		assert(result == coin)
	}

	@Test
	fun `does cache data source saveCoins calls repository`() = runBlocking {
		stubSave()

		val coins = CoinsDataFactory.coinDatas

		coinsCacheDataSource.saveCoins(coins)

		coVerify(exactly = 1) { coinsCacheRepository.saveCoins(coins) }
	}

	@Test
	fun `does cache data source saveCoin calls repository`() = runBlocking {
		stubSave()

		val coin = CoinsDataFactory.coinData

		coinsCacheDataSource.saveCoin(coin)

		coVerify(exactly = 1) { coinsCacheRepository.saveCoin(coin) }
	}

	private fun stubIsCacheValid(isCacheValid: Boolean) {
		every { coinsCacheRepository.isCacheValid } returns isCacheValid
	}

	private fun stubLastCacheTime(lastCacheTime: Long) {
		every { coinsCacheRepository.lastCacheTime } returns lastCacheTime
		every { coinsCacheRepository.lastCacheTime = any() } returns Unit
	}

	private fun stubCoins(coins: List<CoinData>) = runBlocking {
		coEvery { coinsCacheRepository.getCoins() } returns coins
	}

	private fun stubCoin(coinData: CoinData) = runBlocking {
		coEvery { coinsCacheRepository.getCoin(any()) } returns coinData
	}

	private fun stubSave() = runBlocking {
		coEvery { coinsCacheRepository.saveCoins(any()) } returns Unit
		coEvery { coinsCacheRepository.saveCoin(any()) } returns Unit
	}
}