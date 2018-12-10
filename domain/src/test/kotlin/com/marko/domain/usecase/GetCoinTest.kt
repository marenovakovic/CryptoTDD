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
internal class GetCoinTest {

	private val coinsRepository = mockk<CoinsRepository>()
	private val getCoin = GetCoin(coinsRepository)

	@Test
	fun `does use case calls repository`() = runBlocking {
		val id = 1
		val coinEntity = CoinsFactory.coinEntity
		stubCoin(coinEntity)

		getCoin(id)
		coVerify(exactly = 1) { coinsRepository.getCoin(id) }
	}

	@Test
	fun `check use case result`() = runBlocking {
		val coinEntity = CoinsFactory.coinEntity
		stubCoin(coinEntity)

		val result = getCoin(1)
		assert(result.id == coinEntity.id)
		assert(result.name == coinEntity.name)
		assert(result.symbol == coinEntity.symbol)
	}

	private fun stubCoin(coinEntity: CoinEntity) = runBlocking {
		coEvery { coinsRepository.getCoin(any()) } returns coinEntity
	}
}