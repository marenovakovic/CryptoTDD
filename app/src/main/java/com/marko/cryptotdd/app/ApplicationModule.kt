package com.marko.cryptotdd.app

import android.content.Context
import com.marko.cache.coins.CoinsDatabase
import com.marko.domain.coroutines.CoroutineDispatchers
import com.marko.preferences.PreferencesStorage
import com.marko.preferences.SharedPreferencesStorage
import com.marko.presentation.coroutines.CoroutineDispatchersImpl
import com.marko.remote.api.Api
import com.marko.remote.api.CoinsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ApplicationBindingModule {

	@Binds
	abstract fun preferences(bind: SharedPreferencesStorage): PreferencesStorage

	@Binds
	abstract fun dispatchers(bind: CoroutineDispatchersImpl): CoroutineDispatchers
}

@Module(includes = [ApplicationBindingModule::class])
class ApplicationModule(private val context: Context) {

	@Provides
	fun provideContext(): Context = context

	@Singleton
	@Provides
	fun provideDatabase(context: Context): CoinsDatabase = CoinsDatabase.getInstance(context)

	@Singleton
	@Provides
	fun provideCoinsService(): CoinsService = Api.coinsService
}