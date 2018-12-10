package com.marko.cryptotdd.factory

import com.marko.domain.entities.CoinEntity
import com.marko.presentation.entities.Coin

object CoinsViewFactory {

	val coinEntity: CoinEntity = CoinEntity(
		id = BaseFactory.int,
		name = BaseFactory.string,
		symbol = BaseFactory.string
	)

	fun createCoin(id: Int = 1, name: String = "Bitcoin", symbol: String = "BTC"): Coin =
		Coin(
			id = id,
			name = name,
			symbol = symbol
		)

	val coinEntities: List<CoinEntity> = listOf(coinEntity, coinEntity, coinEntity)

	val coin: Coin = Coin(
		id = BaseFactory.int,
		name = BaseFactory.string,
		symbol = BaseFactory.string
	)

	val coins: List<Coin> = listOf(coin, coin, coin)
}