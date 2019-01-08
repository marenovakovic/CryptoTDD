package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.data.factory.CoinsDataFactory
import com.marko.domain.time.now
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsRemoteDataSourceTest {

	private val coinsRemoteRepository = mockk<CoinsRemoteRepository>()
	private val coinsRemoteDataSource = CoinsRemoteDataSource(coinsRemoteRepository)

	@Test
	fun `does data source getCoins calls repository`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		coinsRemoteDataSource.getCoins()

		coVerify(exactly = 1) { coinsRemoteDataSource.getCoins() }
	}

	@Test
	fun `check remote data source getCoins result`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		val result = coinsRemoteDataSource.getCoins()
		assert(result == coins)
	}

	@Test
	fun `does data source getCoin calls repository`() = runBlocking {
		val coinData = CoinsDataFactory.coinData
		stubCoin(coinData)

		val coinId = 1

		coinsRemoteDataSource.getCoin(coinId)

		coVerify(exactly = 1) { coinsRemoteRepository.getCoin(1) }
	}

	@Test
	fun `check data source getCoin result`() = runBlocking {
		val coinData = CoinsDataFactory.coinData
		stubCoin(coinData)

		val coinId = 1

		val result = coinsRemoteDataSource.getCoin(1)

		assert(result == coinData)
	}

	@Test(expected = IllegalAccessException::class)
	fun `is exception thrown when calling isCacheValid`() {
		coinsRemoteDataSource.isCacheValid
	}

	@Test(expected = IllegalAccessException::class)
	fun `is exception thrown when accessing lastCacheTime`() {
		coinsRemoteDataSource.lastCacheTime
	}

	@Test(expected = IllegalAccessException::class)
	fun `is exception thrown when setting lastCacheTime`() {
		coinsRemoteDataSource.lastCacheTime = now
	}

	@Test(expected = IllegalAccessException::class)
	fun `is exception thrown when calling saveCoins`() = runBlocking<Unit> {
		coinsRemoteDataSource.saveCoins(listOf())
	}

	@Test(expected = IllegalAccessException::class)
	fun `is exception thrown when calling saveCoin`() = runBlocking<Unit> {
		coinsRemoteDataSource.saveCoin(CoinsDataFactory.coinData)
	}

	private fun stubCoins(coins: List<CoinData>) = runBlocking {
		coEvery { coinsRemoteDataSource.getCoins() } returns coins
	}

	private fun stubCoin(coinData: CoinData) = runBlocking {
		coEvery { coinsRemoteRepository.getCoin(any()) } returns coinData
	}
}