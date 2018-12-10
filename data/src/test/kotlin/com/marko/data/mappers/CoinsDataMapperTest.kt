package com.marko.data.mappers

import com.marko.data.entities.CoinData
import com.marko.data.factory.CoinsDataFactory
import com.marko.domain.entities.CoinEntity
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsDataMapperTest {

	@Test
	fun `test CoinEntity to CoinData mapping`() {
		val coinEntity = CoinsDataFactory.coinEntity

		val coinData = coinEntity.toData()

		assertCoins(coinData, coinEntity)
	}

	@Test
	fun `test list of CoinEntity to list of CoinData mapping`() {
		val coinEntities = CoinsDataFactory.coinEntities

		val coinDatas = coinEntities.toData()

		repeat(coinDatas.size) {
			assertCoins(coinDatas[it], coinEntities[it])
		}
	}

	@Test
	fun `test CoinData to CoinEntity mapping`() {
		val coinData = CoinsDataFactory.coinData

		val coinEntity = coinData.toEntity()

		assertCoins(coinData, coinEntity)
	}

	@Test
	fun `test list of CoinData to list of CoinEntity mapping`() {
		val coinDatas = CoinsDataFactory.coinDatas

		val coinEntities = coinDatas.toEntity()

		repeat(coinDatas.size) {
			assertCoins(coinDatas[it], coinEntities[it])
		}
	}

	private fun assertCoins(coinData: CoinData, coinEntity: CoinEntity) {
		assert(coinData.id == coinEntity.id)
		assert(coinData.name == coinEntity.name)
		assert(coinData.symbol == coinEntity.symbol)
	}
}