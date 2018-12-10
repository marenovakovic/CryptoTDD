package com.marko.cache.coins

import org.junit.Test

internal class CoinsDaoTest : DatabaseTest() {

	@Test
	fun `test inserting coins to database`() {
		insertCoins(database)

		assert(coinsDao.getCoins().size == 2)
	}

	@Test
	fun `test retrieving coins from database`() {
		insertCoins(database)

		val result = coinsDao.getCoins()

		assert(result == coins)
	}

	@Test
	fun `test inserting single coin to database`() {
		insertCoin(database)

		assert(coinsDao.getCoins().size == 1)
		assert(coinsDao.getCoins().single() == coinCache)
	}

	@Test
	fun `test retrieving single coin from database`() {
		insertCoin(database)

		val coinId = 1
		assert(coinsDao.getCoin(coinId) == coinCache)
	}
}