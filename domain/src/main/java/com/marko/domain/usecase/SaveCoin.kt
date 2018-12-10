package com.marko.domain.usecase

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import javax.inject.Inject

class SaveCoin @Inject constructor(
	private val coinsRepository: CoinsRepository
) : UseCase<CoinEntity, Unit>() {

	override suspend fun execute(parameters: CoinEntity) =
		coinsRepository.saveCoin(coin = parameters)
}