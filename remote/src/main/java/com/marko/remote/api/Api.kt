package com.marko.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Api {

	private const val READ_TIMEOUT = 15L
	private const val WRITE_TIMEOUT = 15L

	private val client: OkHttpClient = OkHttpClient.Builder()
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
		.cache(Cache(File("cache"), 10 * 1024 * 1024))
		.build()

	private val retrofit: Retrofit = Retrofit.Builder()
		.baseUrl("https://pro-api.coinmarketcap.com/v1/")
		.client(client)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(CoroutineCallAdapterFactory())
		.build()

	val coinsService: CoinsService = retrofit.create(CoinsService::class.java)
}