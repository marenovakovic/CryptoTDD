package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class CoinsResponse(
	@SerializedName("data")
	val coins: List<CoinRemote>,
	val metadata: CoinsMetadata
)