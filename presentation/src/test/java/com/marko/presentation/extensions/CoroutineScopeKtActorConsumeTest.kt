package com.marko.presentation.extensions

import com.marko.domain.test.TestCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoroutineScopeKtActorConsumeTest {

	private val dispatchers = TestCoroutineDispatchers()
	private val job = Job()
	private val scope = CoroutineScope(dispatchers.main + job)

	@Test
	fun `check is element passed back`() = runBlocking {
		val element = 1

		val actor = scope.actorConsumeEach<Int> {
			assert(it == element)
		}

		actor.send(element)
	}
}