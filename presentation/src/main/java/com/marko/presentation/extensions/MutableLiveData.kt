package com.marko.presentation.extensions

import androidx.lifecycle.MutableLiveData
import com.marko.presentation.result.Result
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> MutableLiveData<Result<T>>.safeWithContext(
	context: CoroutineContext,
	f: suspend () -> T
) {
	postValue(Result.Loading)

	postValue(
		try {
			withContext(context) { Result.Success(f()) }
		} catch (e: Exception) {
			Result.Error(e)
		}
	)
}