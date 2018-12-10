package com.marko.cache.factory

import com.marko.cache.entities.CoinCache
import com.marko.data.entities.CoinData

object CoinsCacheFactory {

	val coinCache: CoinCache = CoinCache(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinCaches: List<CoinCache> = listOf(coinCache, coinCache.copy(id = 2), coinCache.copy(id = 3))

	val coinData: CoinData = CoinData(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinDatas: List<CoinData> = listOf(coinData, coinData.copy(id = 2), coinData.copy(id = 3))
}