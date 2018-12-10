package com.marko.cache.coins

import com.marko.cache.entities.CoinCache

val coinCache: CoinCache = CoinCache(
	id = 1,
	name = "Bitcoin",
	symbol = "BTC"
)

val coinCache2: CoinCache = CoinCache(
	id = 2,
	name = "Etherium",
	symbol = "ETH"
)

val coins = listOf(coinCache, coinCache2)

fun insertCoins(database: CoinsDatabase) = database.coinsDao().insertCoins(coins)

fun insertCoin(database: CoinsDatabase) = database.coinsDao().insertCoin(coinCache)