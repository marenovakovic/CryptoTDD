package com.marko.cache.coins

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
abstract class DatabaseTest {

	@Rule
	@JvmField
	val instantTaskExecutorRule = InstantTaskExecutorRule()

	private val context = ApplicationProvider.getApplicationContext<Context>()
	protected lateinit var database: CoinsDatabase
	protected lateinit var coinsDao: CoinsDao

	@Before
	fun setup() {
		database = Room
			.inMemoryDatabaseBuilder(context, CoinsDatabase::class.java)
			.allowMainThreadQueries()
			.build()

		coinsDao = database.coinsDao()
	}

	@After
	@Throws(IOException::class)
	fun cleanup() = database.close()
}