package com.marko.presentation.coroutines

import com.marko.domain.coroutines.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * [CoroutineDispatchers] implementation
 */
@Singleton
class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
	override val main: CoroutineContext
		get() = Dispatchers.Main

	override val io: CoroutineContext
		get() = Dispatchers.IO

	override val async: CoroutineContext
		get() = Dispatchers.Default
}