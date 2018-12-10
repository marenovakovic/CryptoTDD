package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.coroutines.CoroutineDispatchers
import com.marko.domain.usecase.GetCoins
import com.marko.domain.usecase.invoke
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entities.Coin
import com.marko.presentation.extensions.actorConsumeEach
import com.marko.presentation.extensions.safeWithContext
import com.marko.presentation.mappers.toPresentation
import com.marko.presentation.result.Result
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Loads [Coin] data and exposes it to the View
 *
 * @param dispatchers [CoroutineDispatchers]
 *
 * @param getCoins [GetCoins] use case that communicates with Data Layer
 */
class CoinsViewModel @Inject constructor(
	private val dispatchers: CoroutineDispatchers,
	private val getCoins: GetCoins
) : BaseViewModel(dispatchers) {

	/**
	 * [MutableLiveData] that holds [Result]<List<Coin>> value, exposed as [LiveData]
	 */
	private val _result = MutableLiveData<Result<List<Coin>>>()
	val result: LiveData<Result<List<Coin>>>
		get() = _result

	/**
	 * [actor] constructed with [actorConsumeEach] that will handle [CoinAction]s
	 */
	private val actor = actorConsumeEach<CoinAction> { action ->
		when (action) {
			is FetchCoins ->
				_result.safeWithContext(dispatchers.io) { getCoins().map { it.toPresentation() } }
		}
	}

	/**
	 * Sends [FetchCoins] action to [actor]
	 */
	fun fetch() = launch { actor.send(FetchCoins) }
}