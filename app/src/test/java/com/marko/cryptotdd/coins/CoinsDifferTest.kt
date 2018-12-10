package com.marko.cryptotdd.coins

import com.marko.cryptotdd.factory.CoinsViewFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsDifferTest {

	@Test
	fun `test CoinsDiffer items are the same`() {
		val coin = CoinsViewFactory.coin
		val otherCoin = coin

		assert(CoinsDiffer.areItemsTheSame(coin, otherCoin))
	}

	@Test
	fun `test CoinsDiffer items are NOT the same`() {
		val coin = CoinsViewFactory.createCoin(id = 1)
		val otherCoin = CoinsViewFactory.createCoin(id = 2)

		assert(! CoinsDiffer.areItemsTheSame(coin, otherCoin))
	}

	@Test
	fun `tests CoinsDiffer content is the same`() {
		val coin = CoinsViewFactory.coin
		val otherCoin = coin

		assert(CoinsDiffer.areContentsTheSame(coin, otherCoin))
	}

	@Test
	fun `tests CoinsDiffer content is NOT the same`() {
		val coin = CoinsViewFactory.createCoin(name = "coin")
		val otherCoin = CoinsViewFactory.createCoin(name = "otherCoin")

		assert(! CoinsDiffer.areContentsTheSame(coin, otherCoin))
	}
}