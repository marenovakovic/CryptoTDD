package com.marko.remote.factory

import com.marko.domain.time.now
import com.marko.remote.entities.CoinResponse
import com.marko.remote.entities.CoinsMetadata
import com.marko.remote.entities.CoinsResponse

object ResponseFactory {

	val metadata = CoinsMetadata(
		timestamp = now,
		numberOfCoins = 2,
		error = Any()
	)

	val coinsResponse = CoinsResponse(
		coins = CoinsRemoteFactory.coinRemotes,
		metadata = metadata
	)

	val coinResponse = CoinResponse(
		coin = CoinsRemoteFactory.coinRemote,
		metadata = metadata
	)
}