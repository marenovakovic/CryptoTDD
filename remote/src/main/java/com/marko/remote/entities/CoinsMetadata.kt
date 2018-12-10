package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class CoinsMetadata(
	val timestamp: Long,
	@SerializedName("num_cryptocurrencies")
	val numberOfCoins: Int? = null,
	val error: Any? = null
)