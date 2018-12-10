package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.factory.CoinsFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetCoinsTest {

	private val coinsRepository = mockk<CoinsRepository>()
	private val getCoins = GetCoins(coinsRepository)

	@Test
	fun `check getCoins calls repository`() = runBlocking {
		val coins = CoinsFactory.coinEntities
		stubCoins(coins)

		getCoins()
		coVerify(exactly = 1) { coinsRepository.getCoins() }
	}

	@Test
	fun `check getCoins result`() = runBlocking {
		val coins = CoinsFactory.coinEntities
		stubCoins(coins)

		val result = getCoins()
		assert(result == coins)
	}

	private fun stubCoins(coins: List<CoinEntity>) = runBlocking {
		coEvery { coinsRepository.getCoins() } returns coins
	}
}