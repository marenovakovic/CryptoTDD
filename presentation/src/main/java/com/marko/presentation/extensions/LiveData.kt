package com.marko.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observeNonNull(
	owner: LifecycleOwner,
	crossinline observer: (T) -> Unit
) {
	val mediator = MediatorLiveData<T>()

	mediator.addSource(this) {
		it?.let { mediator.value = it }
	}

	mediator.observe(owner, Observer { it?.let { observer(it) } })
}