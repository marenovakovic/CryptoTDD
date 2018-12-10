package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class CoinResponse(
	@SerializedName("data")
	val coin: CoinRemote,
	val metadata: CoinsMetadata
)