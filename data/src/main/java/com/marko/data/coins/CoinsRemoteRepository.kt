package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId

/**
 * Repository that describes actions that should be performed by remote repository
 */
interface CoinsRemoteRepository {

	/**
	 * Fetch [List] of [CoinData] from API
	 *
	 * @return [List] of [CoinData] fetched from API
	 */
	suspend fun getCoins(): List<CoinData>

	/**
	 * Fetch [CoinData] details fetched from API
	 *
	 * @param coinId [CoinId] of requested [CoinData]
	 *
	 * @return Requested [CoinData]
	 */
	suspend fun getCoin(coinId: CoinId): CoinData
}