package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import com.marko.domain.coroutines.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * [ViewModel] base class that implements [CoroutineScope]
 *
 * @param dispatchers [CoroutineDispatchers]
 */
abstract class BaseViewModel(
	private val dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope {

	/**
	 * Parent [Job]
	 */
	private val job = Job()

	/**
	 * [CoroutineContext] for [BaseViewModel] [CoroutineScope]
	 * [Dispatchers.Main] + [job]
	 */
	override val coroutineContext: CoroutineContext
		get() = dispatchers.main + job

	/**
	 * Cancel the [job] because it's no longer needed
	 */
	override fun onCleared() {
		super.onCleared()

		job.cancel()
	}
}