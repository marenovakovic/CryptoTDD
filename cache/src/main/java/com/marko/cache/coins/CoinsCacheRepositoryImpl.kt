package com.marko.cache.coins

import com.marko.cache.mappers.toCache
import com.marko.cache.mappers.toData
import com.marko.data.coins.CoinsCacheRepository
import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId
import com.marko.domain.time.now
import com.marko.preferences.PreferencesStorage
import javax.inject.Inject
import javax.inject.Singleton

private const val SECOND = 60
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR
const val TEN_DAYS = 10 * DAY

@Singleton
class CoinsCacheRepositoryImpl @Inject constructor(
	private val database: CoinsDatabase,
	private val preferencesStorage: PreferencesStorage
) : CoinsCacheRepository {

	override val isCacheValid: Boolean
		get() = now - preferencesStorage.lastCacheTime < TEN_DAYS

	override suspend fun getCoins(): List<CoinData> = database.coinsDao().getCoins().toData()

	override suspend fun getCoin(coinId: CoinId): CoinData =
		database.coinsDao().getCoin(coinId).toData()

	override suspend fun saveCoins(coins: List<CoinData>) =
		database.coinsDao().insertCoins(coins.toCache())

	override suspend fun saveCoin(coinData: CoinData) =
		database.coinsDao().insertCoin(coinData.toCache())
}