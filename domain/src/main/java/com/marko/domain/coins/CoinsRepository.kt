package com.marko.domain.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.entities.CoinId

/**
 * Repository that is responsible to fetch [CoinEntity] related content
 */
interface CoinsRepository {

	/**
	 * Fetches  [List] of [CoinEntity]s
	 *
	 * @return [List] of [CoinEntity]s
	 */
	suspend fun getCoins(): List<CoinEntity>

	/**
	 * Fetches [CoinEntity] details
	 *
	 * @param coinId [CoinId] of wanted [CoinEntity]
	 *
	 * @return [CoinEntity] details
	 */
	suspend fun getCoin(coinId: CoinId): CoinEntity

	/**
	 * Saves given [CoinEntity] [List]
	 *
	 * @param coins [CoinEntity] [List] to be saved
	 */
	suspend fun saveCoins(coins: List<CoinEntity>)

	/**
	 * Saves given [CoinEntity]
	 *
	 * @param coin [CoinEntity] to be saved
	 */
	suspend fun saveCoin(coin: CoinEntity)
}