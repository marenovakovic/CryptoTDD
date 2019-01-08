package com.marko.cache.coins

import android.content.Context
import com.marko.cache.factory.CoinsCacheFactory
import com.marko.cache.mappers.toData
import com.marko.domain.time.now
import com.marko.preferences.PreferencesStorage
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CoinsCacheRepositoryImplTest {

	private val context = ApplicationProvider.getApplicationContext<Context>()
	private val database =
		Room.inMemoryDatabaseBuilder(context, CoinsDatabase::class.java)
			.allowMainThreadQueries()
			.build()

	private val preferencesStorage = mockk<PreferencesStorage>()
	private val coinsCacheRepositoryImpl = CoinsCacheRepositoryImpl(database, preferencesStorage)

	@Test
	fun `test isCacheValid is valid`() {
		stubLastCacheTime(now)

		assert(coinsCacheRepositoryImpl.isCacheValid)
	}

	@Test
	fun `test isCacheValid is invalid`() {
		stubLastCacheTime(now - TEN_DAYS * 2)

		assert(! coinsCacheRepositoryImpl.isCacheValid)
	}

	@Test
	fun `test saveCoins`() = runBlocking {
		val coins = CoinsCacheFactory.coinCaches

		coinsCacheRepositoryImpl.saveCoins(coins.toData())

		assert(coinsCacheRepositoryImpl.getCoins() == coins.toData())
	}

	@Test
	fun `test saveCoin`() = runBlocking {
		val coin = CoinsCacheFactory.coinCache

		coinsCacheRepositoryImpl.saveCoin(coin.toData())

		assert(coinsCacheRepositoryImpl.getCoins().single() == coin.toData())
	}

	@Test
	fun `test getCoins`() = runBlocking {
		coinsCacheRepositoryImpl.getCoins()
		Unit
	}

	private fun stubLastCacheTime(lastCacheTime: Long) {
		every { preferencesStorage.lastCacheTime } returns lastCacheTime
	}
}