package com.marko.presentation.coins

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.test.TestCoroutineDispatchers
import com.marko.domain.usecase.GetCoins
import com.marko.domain.usecase.invoke
import com.marko.presentation.entities.Coin
import com.marko.presentation.factory.CoinsPresentationFactory
import com.marko.presentation.mappers.toPresentation
import com.marko.presentation.result.Result
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	private val dispatchers = TestCoroutineDispatchers()
	private val coinsRepository = mockk<CoinsRepository>()
	private val getCoins = GetCoins(coinsRepository)
	private val viewModel = CoinsViewModel(dispatchers, getCoins)

	@Test
	fun `does ViewModel result starts with Result Loading`() = runBlocking {
		val coins = CoinsPresentationFactory.coinEntities
		stubCoins(coins)

		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		verify { observer.onChanged(Result.Loading) }
	}

	@Test
	fun `test ViewModel successful fetch`() = runBlocking {
		val coins = CoinsPresentationFactory.coinEntities
		stubCoins(coins)

		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		verify { observer.onChanged(Result.Success(coins.toPresentation())) }
	}

	@Test
	fun `check ViewModel successful fetch result`() = runBlocking {
		val coins = CoinsPresentationFactory.coinEntities
		stubCoins(coins)

		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		assert(viewModel.result.value == Result.Success(coins.toPresentation()))
	}

	@Test
	fun `test ViewModel error fetch`() = runBlocking {
		val exception = Exception("Test error")
		stubError(exception)

		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		verify { observer.onChanged(Result.Error(exception)) }
	}

	@Test
	fun `check ViewModel error fetch result`() = runBlocking {
		val exception = Exception("Test error")
		stubError(exception)

		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		assert(viewModel.result.value == Result.Error(exception))
	}

	@Test
	fun `does fetch calls use case`() = runBlocking {
		val observer = mockObserver<Result<List<Coin>>>()
		stubOnChange(observer)

		viewModel.result.observeForever(observer)
		viewModel.fetch()

		coVerify(exactly = 1) { getCoins() }
	}

	private suspend fun stubCoins(coins: List<CoinEntity>) = runBlocking {
		coEvery { coinsRepository.getCoins() } returns coins
	}

	private inline fun <reified T : Any> stubOnChange(observer: Observer<T>) {
		every { observer.onChanged(any()) } returns Unit
	}

	private fun stubError(exception: Exception) = runBlocking {
		coEvery { coinsRepository.getCoins() } throws exception
	}

	private fun <T> mockObserver() = mockk<Observer<T>>()
}