package com.marko.remote.api

import com.marko.domain.entities.CoinId
import com.marko.remote.entities.CoinResponse
import com.marko.remote.entities.CoinsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API for fetching [CoinsResponse] from server
 */
interface CoinsService {

	/**
	 * Fetches [CoinsResponse] from server
	 *
	 * @return [Deferred] containing [CoinsResponse]
	 */
	@GET("cryptocurrency/listings/latest")
	fun getCoins(@Query(value = "CMC_PRO_API_KEY") apiKey: String = "API_KEY"): Deferred<CoinsResponse>

	/**
	 * Fetches [CoinResponse] from server
	 *
	 * @return [Deferred] containing [CoinResponse]
	 */
	@GET("ticker/{id}/")
	fun getCoin(@Path("id") coinId: CoinId): Deferred<CoinResponse>
}