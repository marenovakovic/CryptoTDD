package com.marko.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Cache layer description of crypto currency
 * Room [Entity]
 */
@Entity(tableName = "coins")
data class CoinCache(
	/**
	 * Unique [Int] identifying crypto currency
	 * [PrimaryKey]
	 */
	@PrimaryKey(autoGenerate = true)
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