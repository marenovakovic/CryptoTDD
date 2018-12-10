package com.marko.presentation.result

/**
 * Generic class that holds value
 */
sealed class Result<out R> {

	/**
	 * Success value
	 *
	 * @param data<T> data that is returned on success case
	 */
	data class Success<out T>(val data: T) : Result<T>()

	/**
	 * Error value
	 *
	 * @param exception [Exception] thrown during execution
	 */
	data class Error(val exception: Exception) : Result<Nothing>()

	/**
	 * Loading value
	 */
	object Loading : Result<Nothing>()
}

/**
 * Map [Result] value
 *
 * @receiver [Result]<R> that is being mapped to [Result]<T>
 *
 * @param transform mapping function that taked R and returns T
 *
 * @return [Result]<T> that is mapped from [Result]<R>
 */
fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> = when (this) {
	is Result.Loading -> this
	is Result.Success -> Result.Success(transform(this.data))
	is Result.Error -> this
}