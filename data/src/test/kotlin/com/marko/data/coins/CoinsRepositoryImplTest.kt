package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.data.factory.CoinsDataFactory
import com.marko.data.mappers.toEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsRepositoryImplTest {

	private val sourceMediator = mockk<CoinsDataSourceMediator>()
	private val coinsRepositoryImpl = CoinsRepositoryImpl(sourceMediator)

	@Test
	fun `does getCoins calls source mediator`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		coinsRepositoryImpl.getCoins()
		coVerify(exactly = 1) { sourceMediator.getCoins() }
	}

	@Test
	fun `check getCoins result`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas
		stubCoins(coins)

		val result = coinsRepositoryImpl.getCoins()

		assert(result == coins.toEntity())
	}

	@Test
	fun `does getCoin calls source mediator`() = runBlocking {
		val coin = CoinsDataFactory.coinData
		stubCoin(coin)

		val coinId = 1

		coinsRepositoryImpl.getCoin(coinId)
		coVerify(exactly = 1) { sourceMediator.getCoin(coinId) }
	}

	@Test
	fun `check getCoin result`() = runBlocking {
		val coin = CoinsDataFactory.coinData
		stubCoin(coin)

		val coinId = 1

		val result = coinsRepositoryImpl.getCoin(coinId)

		assert(result == coin.toEntity())
	}

	@Test
	fun `does saveCoins calls source mediator`() = runBlocking {
		val coins = CoinsDataFactory.coinDatas

		stubSave()

		coinsRepositoryImpl.saveCoins(coins.toEntity())

		coVerify(exactly = 1) { sourceMediator.saveCoins(coins) }
	}

	@Test
	fun `does saveCoin calls source mediator`() = runBlocking {
		val coin = CoinsDataFactory.coinData

		stubSave()

		coinsRepositoryImpl.saveCoin(coin.toEntity())

		coVerify(exactly = 1) { sourceMediator.saveCoin(coin) }
	}

	private fun stubCoins(coins: List<CoinData>) = runBlocking {
		coEvery { sourceMediator.getCoins() } returns coins
	}

	private fun stubCoin(coin: CoinData) = runBlocking {
		coEvery { sourceMediator.getCoin(any()) } returns coin
	}

	private fun stubSave() = runBlocking {
		coEvery { sourceMediator.saveCoins(any()) } returns Unit
		coEvery { sourceMediator.saveCoin(any()) } returns Unit
	}
}