package com.marko.domain.coroutines

import kotlin.coroutines.CoroutineContext

/**
 * Coroutine [Dispatcher]s abstraction
 */
interface CoroutineDispatchers {

	/**
	 * Main [CoroutineContext] [Dispatchers.Main]
	 */
	val main: CoroutineContext

	/**
	 * IO [CoroutineContext] [Dispatchers.IO]
	 */
	val io: CoroutineContext

	/**
	 * Async [CoroutineContext] [Dispatchers.Default]
	 */
	val async: CoroutineContext
}