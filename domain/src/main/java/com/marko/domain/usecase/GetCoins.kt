package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import javax.inject.Inject

/**
 * [UseCase] that fetches [CoinEntity]s
 *
 * @param coinsRepository [CoinsRepository] that [UseCase] will interact with
 */
class GetCoins @Inject constructor(
	private val coinsRepository: CoinsRepository
) : UseCase<Unit, List<CoinEntity>>() {

	override suspend fun execute(parameters: Unit): List<CoinEntity> = coinsRepository.getCoins()
}