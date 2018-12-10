package com.marko.cache.coins

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marko.cache.entities.CoinCache
import com.marko.domain.entities.CoinId

/**
 * [Dao] for manipulating [CoinCache]
 */
@Dao
interface CoinsDao {

	/**
	 * Insert [CoinCache] [List] into database with [OnConflictStrategy.REPLACE]
	 *
	 * @param coins [CoinCache] [List] to be saved into database
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCoins(coins: List<CoinCache>)

	/**
	 * Insert [CoinCache] into database with [OnConflictStrategy.REPLACE]
	 *
	 * @param coinCache [CoinCache] to be saved into database
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCoin(coinCache: CoinCache)

	/**
	 * Get all [CoinCache] saved into database
	 *
	 * @return [List] of all [CoinCache] saved into database
	 */
	@Query("SELECT * FROM coins")
	fun getCoins(): List<CoinCache>

	/**
	 * Get single [CoinCache] saved into database
	 *
	 * @param coinId [CoinId] of wanted [CoinCache]
	 *
	 * @return [CoinCache] corresponding to passed [CoinId]
	 */
	@Query("SELECT * FROM coins WHERE id = :coinId")
	fun getCoin(coinId: CoinId): CoinCache
}