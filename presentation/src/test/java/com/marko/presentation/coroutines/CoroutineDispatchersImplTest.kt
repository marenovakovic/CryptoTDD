package com.marko.presentation.coroutines

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoroutineDispatchersImplTest {

	private val dispatchers = CoroutineDispatchersImpl()

	@Test
	fun `check if main dispatcher === Dispatchers Main`() {
		assert(dispatchers.main === Dispatchers.Main)
	}

	@Test
	fun `check if io dispatcher == Dispatchers IO`() {
		assert(dispatchers.io == Dispatchers.IO)
	}

	@Test
	fun `check if async dispatcher == Dispatcher Default`() {
		assert(dispatchers.async == Dispatchers.Default)
	}
}