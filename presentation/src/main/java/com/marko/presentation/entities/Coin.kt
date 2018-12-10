package com.marko.presentation.entities

/**
 * Crypto currency representation for Presentation Layer
 */
data class Coin(
	/**
	 * Unique identifier for each crypto currency ([Coin])
	 */
	val id: Int,

	/**
	 * Name of the crypto currency
	 */
	val name: String,

	/**
	 * Symbol of the crypto currency
	 */
	val symbol: String
)