package com.marko.cryptotdd.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.cryptotdd.R
import com.marko.cryptotdd.coins.CoinsDiffer
import com.marko.cryptotdd.extensions.inflate
import com.marko.presentation.entities.Coin
import kotlinx.android.synthetic.main.list_item_coin.view.*

class CoinsAdapter : ListAdapter<Coin, CoinsAdapter.CoinHolder>(CoinsDiffer) {

	var coins: List<Coin>
		set(value) = submitList(value)
		get() = throw IllegalAccessException("CoinsAdapter.coins is set only propery")

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
		val view = parent.inflate(R.layout.list_item_coin)
		return CoinHolder(view)
	}

	override fun onBindViewHolder(holder: CoinHolder, position: Int) =
		holder.bind(getItem(position))

	inner class CoinHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(coin: Coin) {
			itemView.listItemCoinId.text = coin.id.toString()
			itemView.listItemCoinName.text = coin.name
			itemView.listItemCoinSymbol.text = coin.symbol
		}
	}
}