package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.data.factory.CoinsDataFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsDataSourceMediatorTest {

	private val cacheDataSource = mockk<CoinsCacheDataSource>()
	private val remoteDataSource = mockk<CoinsRemoteDataSource>()
	private val sourceMediator = CoinsDataSourceMediator(cacheDataSource, remoteDataSource)

	@Test
	fun `does getCoins calls cache data source when cache is valid`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas

		stubIsCacheValid(true)
		stubCoins(coins)

		sourceMediator.getCoins()

		coVerify(exactly = 1) { cacheDataSource.getCoins() }
	}

	@Test
	fun `does getCoins calls remote data source when cache is invalid`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas

		stubIsCacheValid(false)
		stubCoins(coins)

		sourceMediator.getCoins()

		coVerify(exactly = 1) { remoteDataSource.getCoins() }
	}

	@Test
	fun `check getCoins result when cache is valid`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas

		stubIsCacheValid(true)
		stubCoins(coins)

		val result = sourceMediator.getCoins()

		assert(result == coins)
	}

	@Test
	fun `check getCoins result when cache is invalid`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas

		stubIsCacheValid(false)
		stubCoins(coins)

		val result = sourceMediator.getCoins()

		assert(result == coins)
	}

	@Test
	fun `does getCoin calls cache data source when cache is valid`() = runBlocking {
		val coin = CoinsDataFactory.coinData

		stubIsCacheValid(true)
		stubCoin(coin)

		val coinId = 1

		sourceMediator.getCoin(coinId)

		coVerify(exactly = 1) { cacheDataSource.getCoin(coinId) }
	}

	@Test
	fun `does getCoin calls remote data source when cache is invalid`() = runBlocking {
		val coin = CoinsDataFactory.coinData

		stubIsCacheValid(false)
		stubCoin(coin)

		val coinId = 1

		sourceMediator.getCoin(coinId)

		coVerify(exactly = 1) { remoteDataSource.getCoin(coinId) }
	}

	@Test
	fun `check getCoin result when cache is valid`() = runBlocking {
		val coin = CoinsDataFactory.coinData

		stubIsCacheValid(true)
		stubCoin(coin)

		val coinId = 1

		val result = sourceMediator.getCoin(coinId)

		assert(result == coin)
	}

	@Test
	fun `check getCoin result when cache is invalid`() = runBlocking {
		val coin = CoinsDataFactory.coinData

		stubIsCacheValid(false)
		stubCoin(coin)

		val coinId = 1

		val result = sourceMediator.getCoin(coinId)

		assert(result == coin)
	}

	@Test
	fun `does saveCoins calls cache data source and doesn't call remote data source`() = runBlocking {
		stubSave()

		val coins = CoinsDataFactory.coinDatas

		sourceMediator.saveCoins(coins)

		coVerify(exactly = 1) { cacheDataSource.saveCoins(coins) }
		coVerify(exactly = 0) { remoteDataSource.saveCoins(coins) }
	}

	@Test
	fun `does saveCoin calls cache data source and doesn't call remote data source`() = runBlocking {
		stubSave()

		val coin = CoinsDataFactory.coinData

		sourceMediator.saveCoin(coin)

		coVerify(exactly = 1) { cacheDataSource.saveCoin(coin) }
		coVerify(exactly = 0) { remoteDataSource.saveCoin(coin) }
	}

	private fun stubCoins(coins: List<CoinData>) = runBlocking {
		coEvery { cacheDataSource.getCoins() } returns coins
		coEvery { remoteDataSource.getCoins() } returns coins
	}

	private fun stubCoin(coinData: CoinData) = runBlocking {
		coEvery { cacheDataSource.getCoin(any()) } returns coinData
		coEvery { remoteDataSource.getCoin(any()) } returns coinData
	}

	private fun stubSave() = runBlocking {
		coEvery { cacheDataSource.saveCoins(any()) } returns Unit
		coEvery { cacheDataSource.saveCoin(any()) } returns Unit
	}

	private fun stubIsCacheValid(isCacheValid: Boolean) {
		every { cacheDataSource.isCacheValid } returns isCacheValid
	}
}