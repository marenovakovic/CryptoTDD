package com.marko.cache.mappers

import com.marko.cache.entities.CoinCache
import com.marko.data.entities.CoinData

/**
 * Mapping [CoinData] to [CoinCache]
 *
 * @receiver [CoinData] that is mapped to [CoinCache]
 *
 * @return [CoinCache] mapped from [CoinData]
 */
fun CoinData.toCache(): CoinCache = CoinCache(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinData] [List] to [CoinCache] [List]
 *
 * @receiver [CoinData] [List] that is mapped to [CoinCache] [List]
 *
 * @return [CoinCache] [List] mapped from [CoinData] [List]
 */
fun List<CoinData>.toCache(): List<CoinCache> = map { it.toCache() }

/**
 * Mapping [CoinCache] to [CoinData]
 *
 * @receiver [CoinCache] that is mapped to [CoinData]
 *
 * @return [CoinData] mapped from [CoinCache]
 */
fun CoinCache.toData(): CoinData = CoinData(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinCache] [List] to [CoinData] [List]
 *
 * @receiver [CoinCache] [List] that is mapped to [CoinData] [List]
 *
 * @return [CoinData] [List] mapped from [CoinCache] [List]
 */
fun List<CoinCache>.toData(): List<CoinData> = map { it.toData() }