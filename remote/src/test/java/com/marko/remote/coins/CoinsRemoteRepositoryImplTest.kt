package com.marko.remote.coins

import com.marko.remote.api.CoinsService
import com.marko.remote.coins.CoinsRemoteRepositoryImpl
import com.marko.remote.entities.CoinResponse
import com.marko.remote.entities.CoinsResponse
import com.marko.remote.factory.ResponseFactory
import com.marko.remote.mappers.toData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsRemoteRepositoryImplTest {

	private val coinsService = mockk<CoinsService>()
	private val coinsRemoteRepositoryImpl =
		CoinsRemoteRepositoryImpl(coinsService)

	@Test
	fun `does getCoins calls service`() = runBlocking {
		val response = ResponseFactory.coinsResponse
		stubCoinsResponse(response)

		coinsRemoteRepositoryImpl.getCoins()

		coVerify(exactly = 1) { coinsService.getCoins() }
	}

	@Test
	fun `check getCoins result`() = runBlocking {
		val response = ResponseFactory.coinsResponse
		stubCoinsResponse(response)

		val result = coinsRemoteRepositoryImpl.getCoins()

		assert(result == response.coins.toData())
	}

	@Test
	fun `does getCoin calls service`() = runBlocking {
		val response = ResponseFactory.coinResponse
		stubCoinResponse(response)

		val coinId = 1
		coinsRemoteRepositoryImpl.getCoin(coinId)

		coVerify(exactly = 1) { coinsService.getCoin(coinId) }
	}

	@Test
	fun `check getCoin result`() = runBlocking {
		val response = ResponseFactory.coinResponse
		stubCoinResponse(response)

		val coinId = 1
		val result = coinsRemoteRepositoryImpl.getCoin(coinId)

		assert(result == response.coin.toData())
	}

	private fun stubCoinsResponse(response: CoinsResponse) = runBlocking {
		coEvery { coinsService.getCoins() } returns CompletableDeferred(response)
	}

	private fun stubCoinResponse(response: CoinResponse) = runBlocking {
		coEvery { coinsService.getCoin(any()) } returns CompletableDeferred(response)
	}
}