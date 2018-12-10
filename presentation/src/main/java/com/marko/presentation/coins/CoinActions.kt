package com.marko.presentation.coins

/**
 * Actions that will be handled by actor
 */
sealed class CoinAction

/**
 * Action signaling that [Coin]s should be fetched
 */
object FetchCoins : CoinAction()