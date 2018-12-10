package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.factory.CoinsFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SaveCoinsTest {

	private val coinsRepository = mockk<CoinsRepository>()
	private val saveCoins = SaveCoins(coinsRepository)

	@Test
	fun `does use case calls repository`() = runBlocking {
		val coins = CoinsFactory.coinEntities
		stubSave()

		saveCoins(coins)

		coVerify(exactly = 1) { coinsRepository.saveCoins(coins) }
	}

	private fun stubSave() = runBlocking {
		coEvery { coinsRepository.saveCoins(any()) } returns Unit
	}
}