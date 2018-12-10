package com.marko.cache.coins

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.cache.entities.CoinCache

@Database(entities = [CoinCache::class], version = 1, exportSchema = false)
abstract class CoinsDatabase : RoomDatabase() {

	abstract fun coinsDao(): CoinsDao

	companion object {
		var instance: CoinsDatabase? = null

		@Synchronized
		fun getInstance(context: Context): CoinsDatabase =
			if (instance != null) instance !!
			else Room.databaseBuilder(context, CoinsDatabase::class.java, "coins.db")
				.build()
				.also { instance = it }
	}
}