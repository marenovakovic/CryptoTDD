package com.marko.remote.mappers

import com.marko.data.entities.CoinData
import com.marko.remote.entities.CoinRemote
import com.marko.remote.factory.CoinsRemoteFactory
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CoinsRemoteMapperTest {

	@Test
	fun `test CoinData to CoinRemote mapping`() {
		val coinData = CoinsRemoteFactory.coinData
		val coinRemote = coinData.toRemote()

		assertCoins(coinRemote, coinData)
	}

	@Test
	fun `test CoinData list to CoinRemote list mapping`() {
		val coinDatas = CoinsRemoteFactory.coinDatas
		val coinRemotes = coinDatas.toRemote()

		repeat(coinDatas.size) {
			assertCoins(coinRemotes[it], coinDatas[it])
		}
	}

	@Test
	fun `test CoinRemote to CoinData mapping`() {
		val coinRemote = CoinsRemoteFactory.coinRemote
		val coinData = coinRemote.toData()

		assertCoins(coinRemote, coinData)
	}

	@Test
	fun `test CoinRemote list to CoinData list mapping`() {
		val coinRemotes = CoinsRemoteFactory.coinRemotes
		val coinDatas = coinRemotes.toData()

		repeat(coinDatas.size) {
			assertCoins(coinRemotes[it], coinDatas[it])
		}
	}

	private fun assertCoins(coinRemote: CoinRemote, coinData: CoinData) {
		assert(coinRemote.id == coinData.id)
		assert(coinRemote.name == coinData.name)
		assert(coinRemote.symbol == coinData.symbol)
	}
}