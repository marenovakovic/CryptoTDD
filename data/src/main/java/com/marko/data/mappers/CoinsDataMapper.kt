package com.marko.data.mappers

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinEntity

/**
 * Mapping [CoinEntity] to [CoinData]
 *
 * @receiver [CoinEntity] that is mapped to [CoinData]
 *
 * @return [CoinEntity] mapped to [CoinData]
 */
fun CoinEntity.toData(): CoinData = CoinData(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinEntity] [List] to [CoinData] [List]
 *
 * @receiver [CoinEntity] [List] that is mapped to [CoinData] [List]
 *
 * @return [CoinData] [List] mapped from [CoinEntity] [List]
 */
fun List<CoinEntity>.toData(): List<CoinData> = map { it.toData() }

/**
 * Mapping [CoinData] to [CoinEntity]
 *
 * @receiver [CoinData] that is mapped to [CoinEntity]
 *
 * @return [CoinData] mapped to [CoinEntity]
 */
fun CoinData.toEntity(): CoinEntity = CoinEntity(
	id = id,
	name = name,
	symbol = symbol
)

/**
 * Mapping [CoinData] [List] to [CoinEntity] [List]
 *
 * @receiver [CoinData] [List] that is mapped to [CoinEntity] [List]
 *
 * @return [CoinData] [List] mapped from [CoinEntity] [List]
 */
fun List<CoinData>.toEntity(): List<CoinEntity> = map { it.toEntity() }