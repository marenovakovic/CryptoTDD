package com.marko.cache.mappers

import com.marko.cache.entities.CoinCache
import com.marko.cache.factory.CoinsCacheFactory
import com.marko.data.entities.CoinData
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinCacheMapperTest {

	@Test
	fun `test CoinData to CoinCache mapping`() {
		val coinData = CoinsCacheFactory.coinData
		val coinCache = coinData.toCache()

		assertCoins(coinCache, coinData)
	}

	@Test
	fun `test CoinData list to CoinCache list mapping`() {
		val coinDatas = CoinsCacheFactory.coinDatas
		val coinCaches = coinDatas.toCache()

		repeat(coinDatas.size) {
			assertCoins(coinCaches[it], coinDatas[it])
		}
	}

	@Test
	fun `test CoinCache to CoinData mapping`() {
		val coinCache = CoinsCacheFactory.coinCache
		val coinData = coinCache.toData()

		assertCoins(coinCache, coinData)
	}

	@Test
	fun `test CoinCache list to CoinData list mapping`() {
		val coinCaches = CoinsCacheFactory.coinCaches
		val coinDatas = coinCaches.toData()

		repeat(coinCaches.size) {
			assertCoins(coinCaches[it], coinDatas[it])
		}
	}

	private fun assertCoins(coinCache: CoinCache, coinData: CoinData) {
		assert(coinCache.id == coinData.id)
		assert(coinCache.name == coinData.name)
		assert(coinCache.symbol == coinData.symbol)
	}
}