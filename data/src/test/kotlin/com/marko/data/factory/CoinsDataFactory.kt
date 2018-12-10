package com.marko.data.factory

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinEntity

object CoinsDataFactory {

	val coinEntity: CoinEntity = CoinEntity(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinEntities = listOf(coinEntity, coinEntity, coinEntity)

	val coinData: CoinData = CoinData(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinDatas: List<CoinData> = listOf(coinData, coinData, coinData)
}