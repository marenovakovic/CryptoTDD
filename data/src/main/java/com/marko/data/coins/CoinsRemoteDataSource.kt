package com.marko.data.coins

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [CoinsDataSource] implementation that takes care of remote data fetching
 *
 * @param coinsRemoteRepository [CoinsRemoteRepository] that [CoinsRemoteRepository] will interact with
 */
@Singleton
class CoinsRemoteDataSource @Inject constructor(
	private val coinsRemoteRepository: CoinsRemoteRepository
) : CoinsDataSource {

	override val isCacheValid: Boolean
		get() = throw IllegalAccessException("CoinsRemoteDataSource doesn't work with cache")

	override suspend fun getCoins(): List<CoinData> = coinsRemoteRepository.getCoins()

	override suspend fun getCoin(coinId: CoinId): CoinData = coinsRemoteRepository.getCoin(coinId = coinId)

	override suspend fun saveCoins(coins: List<CoinData>) =
		throw IllegalAccessException("CoinsRemoteDataSource doesn't work with cache")

	override suspend fun saveCoin(coinData: CoinData) =
		throw IllegalAccessException("CoinsRemoteDataSource doesn't work with cache")
}