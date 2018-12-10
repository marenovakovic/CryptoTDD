package com.marko.domain.test

import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TestCoroutineDispatchersTest {

	private val dispatchers = TestCoroutineDispatchers()

	@Test
	fun `check main dispatcher == Dispatchers Unconfied`() {
		assert(dispatchers.main == Dispatchers.Unconfined)
	}

	@Test
	fun `check io dispatcher == Dispatchers Unconfied`() {
		assert(dispatchers.io == Dispatchers.Unconfined)
	}

	@Test
	fun `check async dispatcher == Dispatchers Unconfined`() {
		assert(dispatchers.async == Dispatchers.Unconfined)
	}
}