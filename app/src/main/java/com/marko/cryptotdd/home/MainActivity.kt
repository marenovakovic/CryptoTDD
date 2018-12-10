package com.marko.cryptotdd.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.cryptotdd.R
import com.marko.cryptotdd.base.BaseActivity
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.entities.Coin
import com.marko.presentation.extensions.observeNonNull
import com.marko.presentation.result.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

	private val viewModel: CoinsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	private val coinsAdapter = CoinsAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel.fetch()
		viewModel.result.observeNonNull(owner = this, observer = ::handleResult)

		coinsRecyclerView.apply {
			adapter = coinsAdapter
			layoutManager = LinearLayoutManager(context).apply { isItemPrefetchEnabled = true }
			setHasFixedSize(true)
		}
	}

	private fun handleResult(result: Result<List<Coin>>) = when (result) {
		is Result.Loading -> coinsProgressBar.show()
		is Result.Success -> {
			coinsAdapter.coins = result.data
			coinsProgressBar.hide()
		}
		is Result.Error -> {
			result.exception.printStackTrace()
			coinsProgressBar.hide()
		}
	}
}
