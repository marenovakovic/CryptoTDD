package com.marko.data.coins

import com.marko.data.mappers.toData
import com.marko.data.mappers.toEntity
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.entities.CoinId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinsRepositoryImpl @Inject constructor(
	private val sourceMediator: CoinsDataSourceMediator
) : CoinsRepository {

	override suspend fun getCoins(): List<CoinEntity> = sourceMediator.getCoins().toEntity()

	override suspend fun getCoin(coinId: CoinId): CoinEntity =
		sourceMediator.getCoin(coinId = coinId).toEntity()

	override suspend fun saveCoins(coins: List<CoinEntity>) =
		sourceMediator.saveCoins(coins = coins.toData())

	override suspend fun saveCoin(coin: CoinEntity) =
		sourceMediator.saveCoin(coinData = coin.toData())
}