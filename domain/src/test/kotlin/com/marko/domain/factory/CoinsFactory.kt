package com.marko.domain.factory

import com.marko.domain.entities.CoinEntity

object CoinsFactory {

	val coinEntity: CoinEntity = CoinEntity(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinEntities = listOf(coinEntity, coinEntity)
}