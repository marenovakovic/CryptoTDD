package com.marko.cryptotdd.coins

import androidx.recyclerview.widget.DiffUtil
import com.marko.presentation.entities.Coin

object CoinsDiffer : DiffUtil.ItemCallback<Coin>() {
	override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean = oldItem == newItem
}