package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId

/**
 * Repository that describes actions that should be performed by cache repository
 */
interface CoinsCacheRepository {

	/**
	 * Check is cache valid
	 */
	val isCacheValid: Boolean


	/**
	 * Last cache time in milliseconds
	 */
	var lastCacheTime: Long

	/**
	 * Fetch [List] of [CoinData] from cache
	 *
	 * @return [List] of cached [CoinData]s
	 */
	suspend fun getCoins(): List<CoinData>

	/**
	 * Fetch [CoinData] from cache
	 *
	 * @param coinId [CoinId] of requested [CoinData]
	 *
	 * @return Requested [CoinData]
	 */
	suspend fun getCoin(coinId: CoinId): CoinData

	/**
	 * Save given [List] of [CoinData] to cache
	 *
	 * @param coins [List] of [CoinData] to be saved to cache
	 */
	suspend fun saveCoins(coins: List<CoinData>)

	/**
	 * Save given [CoinData] to cache
	 *
	 * @param coinData [CoinData] to be saved to cache
	 */
	suspend fun saveCoin(coinData: CoinData)
}