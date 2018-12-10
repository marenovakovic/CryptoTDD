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
internal class SaveCoinTest {

	private val coinsRepository = mockk<CoinsRepository>()
	private val saveCoin = SaveCoin(coinsRepository)

	@Test
	fun nothing() = runBlocking {
		val coin = CoinsFactory.coinEntity
		stubSave()

		saveCoin(coin)
		coVerify(exactly = 1) { coinsRepository.saveCoin(coin) }
	}

	private fun stubSave() = runBlocking {
		coEvery { coinsRepository.saveCoin(any()) } returns Unit
	}
}