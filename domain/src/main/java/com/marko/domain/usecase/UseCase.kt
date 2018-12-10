package com.marko.domain.usecase

/**
 * Executes business logic
 */
abstract class UseCase<in P, R> {

	/**
	 * This is where business logic is executed
	 *
	 * @return return result of executed code
	 *
	 * @param parameters [UseCase] input parameters
	 */
	protected abstract suspend fun execute(parameters: P): R

	/**
	 * Invokes business logic execution
	 *
	 * @return return result of executed code
	 *
	 * @param parameters [UseCase] input parameters
	 */
	suspend operator fun invoke(parameters: P): R = execute(parameters)
}

/**
 * [UseCase] "overload" for [UseCase] that take no ([Unit]) parameters
 *
 * @receiver [UseCase] with no ([Unit]) parameters
 *
 * @return returns result of (@code)
 */
suspend operator fun <R> UseCase<Unit, R>.invoke(): R = this.invoke(Unit)