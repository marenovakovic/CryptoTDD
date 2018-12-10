package com.marko.cryptotdd.app

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.marko.cache.coins.CoinsDatabase
import com.marko.domain.coroutines.CoroutineDispatchers
import com.marko.domain.test.TestCoroutineDispatchers
import com.marko.preferences.PreferencesStorage
import com.marko.remote.api.CoinsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
abstract class TestApplicationBindingModule {

	@Binds
	abstract fun dispatchers(bind: TestCoroutineDispatchers): CoroutineDispatchers
}

@Module(includes = [TestApplicationBindingModule::class])
class TestApplicationModule {

	@Provides
	fun provideContext(): Context = InstrumentationRegistry.getInstrumentation().context

	@Singleton
	@Provides
	fun providePreferences(): PreferencesStorage = mockk()

	@Singleton
	@Provides
	fun provideService(): CoinsService = mockk()

	@Singleton
	@Provides
	fun provideDatabase(context: Context): CoinsDatabase =
		Room.inMemoryDatabaseBuilder(context, CoinsDatabase::class.java)
			.allowMainThreadQueries()
			.build()
}