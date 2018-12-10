package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.entities.CoinId
import javax.inject.Inject

/**
 * [UseCase] that fetches [CoinEntity] details
 *
 * @param coinsRepository [CoinsRepository] that [UseCase] will interact with
 */
class GetCoin @Inject constructor(
	private val coinsRepository: CoinsRepository
) : UseCase<CoinId, CoinEntity>() {

	override suspend fun execute(parameters: CoinId): CoinEntity =
		coinsRepository.getCoin(coinId = parameters)
}