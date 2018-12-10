package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import javax.inject.Inject

class SaveCoins @Inject constructor(
	private val coinsRepository: CoinsRepository
) : UseCase<List<CoinEntity>, Unit>() {

	override suspend fun execute(parameters: List<CoinEntity>) =
		coinsRepository.saveCoins(coins = parameters)
}