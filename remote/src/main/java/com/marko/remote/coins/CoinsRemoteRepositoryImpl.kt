package com.marko.remote.coins

import com.marko.data.coins.CoinsRemoteRepository
import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinId
import com.marko.remote.api.CoinsService
import com.marko.remote.mappers.toData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [CoinsRemoteRepository] implementation responsible for fetching [CoinRemote] from [CoinsService] API
 *
 * @param coinsService [CoinsService] that fetches [CoinResponse] from server
 */
@Singleton
class CoinsRemoteRepositoryImpl @Inject constructor(
	private val coinsService: CoinsService
) : CoinsRemoteRepository {

	override suspend fun getCoins(): List<CoinData> = coinsService.getCoins().await().coins.toData()

	override suspend fun getCoin(coinId: CoinId): CoinData =
		coinsService.getCoin(coinId).await().coin.toData()
}