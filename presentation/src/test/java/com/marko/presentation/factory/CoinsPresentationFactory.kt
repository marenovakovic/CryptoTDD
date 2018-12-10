package com.marko.presentation.factory

import com.marko.domain.entities.CoinEntity
import com.marko.presentation.entities.Coin

object CoinsPresentationFactory {

	val coinEntity: CoinEntity = CoinEntity(
		id = BaseFactory.int,
		name = BaseFactory.string,
		symbol = BaseFactory.string
	)

	val coinEntities: List<CoinEntity> = listOf(coinEntity, coinEntity, coinEntity)

	val coin: Coin = Coin(
		id = BaseFactory.int,
		name = BaseFactory.string,
		symbol = BaseFactory.string
	)

	val coins: List<Coin> = listOf(coin, coin, coin)
}