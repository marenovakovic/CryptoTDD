package com.marko.presentation.extensions

import androidx.lifecycle.MutableLiveData
import com.marko.presentation.result.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Executes given function in given [CoroutineContext] and returns [Result] including [Result.Loading]
 * Use when [Result.Loading] is needed
 *
 * @param context [CoroutineContext] function should be executed in
 *
 * @param liveData [MutableLiveData] results will be posted on
 *
 * @param f suspend lambda to be executed
 *
 * @return [Result] result of executed function, can be [Result.Success] of [Result.Error]
 */
suspend fun <T> safeWithContext(
	context: CoroutineContext,
	liveData: MutableLiveData<Result<T>>,
	f: suspend () -> T
) {
	liveData.postValue(Result.Loading)

	try {
		withContext(context) { liveData.postValue(Result.Success(f())) }
	} catch (e: Exception) {
		liveData.postValue(Result.Error(e))
	}
}

/**
 * Executes given function in given [CoroutineContext] and returns [Result] without [Result.Loading]
 * Use when [Result.Loading] is not needed
 *
 * @param context [CoroutineContext] function should be executed in
 *
 * @param f suspend lambda to be executed
 *
 * @return [Result] result of executed function, can be [Result.Success] of [Result.Error]
 */
suspend fun <R> safeWithContext(
	context: CoroutineContext,
	f: suspend () -> R
): Result<R> =
	try {
		withContext(context) { Result.Success(f()) }
	} catch (e: Exception) {
		Result.Error(e)
	}

/**
 * Create [actor]<T> that consumes elements
 *
 * @receiver [CoroutineScope] parent scope
 *
 * @param consume suspend lambda that takes consumed action
 *
 * @return [actor]<T> that calls [consume] for every consumed element
 */
fun <T> CoroutineScope.actorConsumeEach(consume: suspend (T) -> Unit) = actor<T> {
	consumeEach { consume(it) }
}