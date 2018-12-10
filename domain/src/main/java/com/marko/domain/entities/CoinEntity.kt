package com.marko.domain.entities

typealias CoinId = Int

/**
 * Domain layer description of crypto currency
 */
data class CoinEntity(
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