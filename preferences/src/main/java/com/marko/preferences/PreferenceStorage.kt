package com.marko.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Storage for app and use preferences
 */
interface PreferencesStorage {
	var lastCacheTime: Long
}

/**
 * [PreferencesStorage] impl backed by [SharedPreferences]
 */
@Singleton
class SharedPreferencesStorage @Inject constructor(
	context: Context
) : PreferencesStorage {

	companion object {
		private const val PREFS_NAME = "coins_tdd"
		private const val LAST_CACHE_TIME = "last_cache_time"
	}

	private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

	override var lastCacheTime: Long by LongPreference(prefs, LAST_CACHE_TIME, - 1)
}

/**
 * [ReadWriteProperty] delegate for storing [Long] values into [SharedPreferences]
 */
class LongPreference(
	private val preferences: SharedPreferences,
	private val name: String,
	private val defaultValue: Long
) : ReadWriteProperty<Any, Long> {

	/**
	 * Get [Long] value from [SharedPreferences]
	 * Must be called from background thread
	 */
	@WorkerThread
	override fun getValue(thisRef: Any, property: KProperty<*>): Long =
		preferences.getLong(name, defaultValue)

	/**
	 * Save [Long] value into [SharedPreferences]
	 * apply called by default
	 */
	override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) =
		preferences.edit { putLong(name, value) }
}