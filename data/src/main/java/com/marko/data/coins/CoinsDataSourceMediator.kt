package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId
import com.marko.domain.injection.DI
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Class that decides which [CoinsDataSource] should be called [CoinsCacheDataSource] or [CoinsRemoteDataSource]
 *
 * @param coinsCacheDataSource [CoinsCacheDataSource] for fetching cached [CoinData]
 *
 * @param coinsRemoteDataSource [CoinsRemoteDataSource] for fetching [CoinData] from API
 */
@Singleton
class CoinsDataSourceMediator @Inject constructor(
	@Named(DI.COINS_CACHE_DATA_SOURCE) private val coinsCacheDataSource: CoinsDataSource,
	@Named(DI.COINS_REMOTE_DATA_SOURCE) private val coinsRemoteDataSource: CoinsDataSource
) {

	/**
	 * Returns [List] of [CoinData] from [CoinsDataSource] after determining which [CoinsDataSource] should be called
	 *
	 * @return [List] of [CoinData] fetched from cache of from API
	 */
	suspend fun getCoins(): List<CoinData> =
		if (coinsCacheDataSource.isCacheValid) coinsCacheDataSource.getCoins()
		else coinsRemoteDataSource.getCoins()

	/**
	 * Returns [CoinData] details from [CoinsDataSource] after determining which [CoinsDataSource] should be called
	 *
	 * @return [CoinData] details fetched from cache of from API
	 */
	suspend fun getCoin(coinId: CoinId): CoinData =
		if (coinsCacheDataSource.isCacheValid) coinsCacheDataSource.getCoin(coinId)
		else coinsRemoteDataSource.getCoin(coinId = coinId)

	/**
	 * Saves [List] of [CoinData] to cache [CoinsCacheDataSource]
	 *
	 * @param coins [List] of [CoinData] to be saved to cache
	 */
	suspend fun saveCoins(coins: List<CoinData>) = coinsCacheDataSource.saveCoins(coins = coins)

	/**
	 * Saves [CoinData] to cache [CoinsCacheDataSource]
	 *
	 * @param coinData [CoinData] to be saved to cache
	 */
	suspend fun saveCoin(coinData: CoinData) = coinsCacheDataSource.saveCoin(coinData = coinData)
}