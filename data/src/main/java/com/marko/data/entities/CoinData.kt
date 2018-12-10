package com.marko.data.entities

/**
 * Data layer description of crypto currency
 */
data class CoinData(
	/**
	 * Unique [Int] identifying crypto currency
	 */
	val id: Int,

	/**
	 * Name of crypto currency
	 */
	val name: String,

	/**
	 * Symbol of crypto currency
	 */
	val symbol: String
)