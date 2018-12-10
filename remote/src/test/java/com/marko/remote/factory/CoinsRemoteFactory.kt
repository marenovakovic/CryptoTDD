package com.marko.remote.factory

import com.marko.data.entities.CoinData
import com.marko.remote.entities.CoinRemote

object CoinsRemoteFactory {

	val coinRemote: CoinRemote = CoinRemote(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinRemotes: List<CoinRemote> = listOf(coinRemote, coinRemote, coinRemote)

	val coinData: CoinData = CoinData(
		id = 1,
		name = "1",
		symbol = "1"
	)

	val coinDatas: List<CoinData> = listOf(coinData, coinData, coinData)
}