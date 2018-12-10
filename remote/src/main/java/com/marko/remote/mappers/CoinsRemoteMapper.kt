package com.marko.remote.mappers

import com.marko.data.entities.CoinData
import com.marko.remote.entities.CoinRemote

/**
 * Mapping [CoinData] to [CoinRemote]
 *
 * @receiver [CoinData] that is mapped to [CoinRemote]
 *
 * @return [CoinRemote] mapped from [CoinData]
 */
fun CoinData.toRemote(): CoinRemote = CoinRemote(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinData] [List] to [CoinRemote] [List]
 *
 * @receiver [CoinData] [List] that is mapped to [CoinRemote] [List]
 *
 * @return [CoinRemote] [List] mapped from [CoinData] [List]
 */
fun List<CoinData>.toRemote(): List<CoinRemote> = map { it.toRemote() }

/**
 * Mapping [CoinRemote] to [CoinData]
 *
 * @receiver [CoinRemote] that is mapped to [CoinData]
 *
 * @return [CoinData] mapped from [CoinRemote]
 */
fun CoinRemote.toData(): CoinData = CoinData(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinRemote] [List] to [CoinData] [List]
 *
 * @receiver [CoinRemote] [List] that is mapped to [CoinData] [List]
 *
 * @return [CoinData] [List] mapped from [CoinRemote] [List]
 */
fun List<CoinRemote>.toData(): List<CoinData> = map { it.toData() }