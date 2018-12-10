package com.marko.remote.entities

/**
 * Remote layer description of crypto currency
 */
data class CoinRemote(
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