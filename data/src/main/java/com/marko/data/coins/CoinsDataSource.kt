package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId

/**
 * DataSource that defines common functionality that repository needs
 */
interface CoinsDataSource {

	/**
	 * Check is data cached
	 */
	val isCacheValid: Boolean

	/**
	 * Last cache time in milliseconds
	 */
	var lastCacheTime: Long

	/**
	 * Fetch [CoinData]s
	 *
	 * @return [List] of fetched [CoinData]
	 */
	suspend fun getCoins(): List<CoinData>

	/**
	 * Fetch [CoinData] details
	 *
	 * @param coinId [CoinId] of requested crypto currency
	 *
	 * @return Details of requested [CoinData]
	 */
	suspend fun getCoin(coinId: CoinId): CoinData

	/**
	 * Save given [CoinData]s
	 *
	 * @param coins [List] of [CoinData]s to be saved
	 */
	suspend fun saveCoins(coins: List<CoinData>)

	/**
	 * Save given [CoinData]
	 *
	 * @param coinData [CoinData] to be saved
	 */
	suspend fun saveCoin(coinData: CoinData)
}