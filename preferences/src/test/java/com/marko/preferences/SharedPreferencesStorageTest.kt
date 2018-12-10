package com.marko.preferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class SharedPreferencesStorageTest {

	private lateinit var context: Context
	private lateinit var preferencesStorage: SharedPreferencesStorage

	@Before
	fun setUp() {
		context = ApplicationProvider.getApplicationContext()
		preferencesStorage = SharedPreferencesStorage(context)
	}

	@Test
	fun `test SharedPreferenceStorage last cache time`() {
		val lastCacheTime = System.currentTimeMillis()

		preferencesStorage.lastCacheTime = lastCacheTime

		assert(preferencesStorage.lastCacheTime == lastCacheTime)
	}
}