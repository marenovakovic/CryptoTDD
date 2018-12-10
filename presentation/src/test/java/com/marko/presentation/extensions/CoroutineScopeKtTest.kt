package com.marko.presentation.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.marko.domain.test.TestCoroutineDispatchers
import com.marko.presentation.result.Result
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoroutineScopeKtTest {

	private val dispatches = TestCoroutineDispatchers()

	@Test
	fun `does safeWithContext starts with ResultLoading`() = runBlocking {
		val observer = mockObserver<Result<Int>>()
		stubOnChange(observer)

		val liveData = MutableLiveData<Result<Int>>()

		val ignore: suspend () -> Int = { 1 }

		safeWithContext(dispatches.io, liveData, ignore)

		liveData.observeForever {
			assert(it is Result.Loading)
		}
	}

//	@Test
//	fun `does safeWithContext returns success when function is executed successfully`() =
//		runBlocking {
//			val liveData = MutableLiveData<Result<Int>>()
//			val result = 5
//			val success: suspend () -> Int = { result }
//
//			safeWithContext(dispatches.io, mockk(), success)
//
//			liveData.observeForever { assert(it is Result.Success) }
//		}

	@Test
	fun `does safeWithContext returns success when function is executed successfully`() =
		runBlocking {
			val success: suspend () -> Int = { 5 }

			val result = safeWithContext(dispatches.io, success)

			assert(result is Result.Success)
		}

	@Test
	fun `check safeWithContext success case result`() = runBlocking {
		val resultData = 5

		val success: suspend () -> Int = { resultData }

		val result = safeWithContext(dispatches.io, success)

		assert((result as Result.Success).data == resultData)
	}

	@Test
	fun `does safeWithContext returns error when function fails with exception`() = runBlocking {
		val exception = RuntimeException()

		val error: suspend () -> Int = { throw exception }

		val result = safeWithContext(dispatches.io, error)

		assert(result is Result.Error)
	}

	@Test
	fun `check safeWithContext error case result`() = runBlocking {
		val exception = RuntimeException()

		val error: suspend () -> Nothing = { throw exception }

		val result = safeWithContext(dispatches.io, error)

		assert((result as Result.Error).exception == exception)
	}

	private inline fun <reified T : Any> stubOnChange(observer: Observer<T>) {
		every { observer.onChanged(any()) } returns Unit
	}

	private fun <T> mockObserver() = mockk<Observer<T>>()
}