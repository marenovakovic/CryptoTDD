package com.marko.domain.test

import com.marko.domain.coroutines.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * [CoroutineDispatchers] abstraction used for testing
 * All dispatchers in this class are [Dispatchers.Unconfined] to allow testing
 */
@Singleton
class TestCoroutineDispatchers @Inject constructor() : CoroutineDispatchers {

	override val main: CoroutineContext
		get() = Dispatchers.Unconfined

	override val io: CoroutineContext
		get() = Dispatchers.Unconfined

	override val async: CoroutineContext
		get() = Dispatchers.Unconfined
}