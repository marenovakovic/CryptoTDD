package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [CoinsDataSource] implementation that takes care of cached data fetching
 *
 * @param coinsCacheRepository [CoinsCacheRepository] that [CoinsCacheDataSource] will interact with
 */
@Singleton
class CoinsCacheDataSource @Inject constructor(
	private val coinsCacheRepository: CoinsCacheRepository
) : CoinsDataSource {

	override val isCacheValid: Boolean
		get() = coinsCacheRepository.isCacheValid

	override var lastCacheTime: Long
		get() = coinsCacheRepository.lastCacheTime
		set(value) { coinsCacheRepository.lastCacheTime = value }

	override suspend fun getCoins(): List<CoinData> = coinsCacheRepository.getCoins()

	override suspend fun getCoin(coinId: CoinId): CoinData =
		coinsCacheRepository.getCoin(coinId = coinId)

	override suspend fun saveCoins(coins: List<CoinData>) =
		coinsCacheRepository.saveCoins(coins = coins)

	override suspend fun saveCoin(coinData: CoinData) =
		coinsCacheRepository.saveCoin(coinData = coinData)
}