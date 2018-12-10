package com.marko.presentation.mappers

import com.marko.domain.entities.CoinEntity
import com.marko.presentation.entities.Coin
import com.marko.presentation.factory.CoinsPresentationFactory
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinPresentationMapperTest {

	@Test
	fun `test CoinEntity to Coin mapping`() {
		val coinEntity = CoinsPresentationFactory.coinEntity
		val coin = coinEntity.toPresentation()

		assertCoins(coin, coinEntity)
	}

	@Test
	fun `test CoinEntity list to Coin list mapping`() {
		val coinEntities = CoinsPresentationFactory.coinEntities
		val coins = coinEntities.toPresentation()

		repeat(coins.size) {
			assertCoins(coins[it], coinEntities[it])
		}
	}

	@Test
	fun `test Coin to CoinEntity mapping`() {
		val coin = CoinsPresentationFactory.coin
		val coinEntity = coin.toEntity()

		assertCoins(coin, coinEntity)
	}

	@Test
	fun `test Coin list to CoinEntity list mapping`() {
		val coins = CoinsPresentationFactory.coins
		val coinEntities  = coins.toEntity()

		repeat(coins.size) {
			assertCoins(coins[it], coinEntities[it])
		}
	}

	private fun assertCoins(coin: Coin, coinEntity: CoinEntity) {
		assert(coin.id == coinEntity.id)
		assert(coin.name == coinEntity.name)
		assert(coin.symbol == coinEntity.symbol)
	}
}