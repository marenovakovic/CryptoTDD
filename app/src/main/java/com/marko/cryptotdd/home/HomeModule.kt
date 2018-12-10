package com.marko.cryptotdd.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.cache.coins.CoinsCacheRepositoryImpl
import com.marko.cryptotdd.injection.ViewModelKey
import com.marko.data.coins.*
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.injection.DI
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.injection.ViewModelFactory
import com.marko.remote.coins.CoinsRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class HomeModule {

	@Binds
	@Named(DI.COINS_CACHE_DATA_SOURCE)
	abstract fun cacheDataSource(bind: CoinsCacheDataSource): CoinsDataSource

	@Binds
	@Named(DI.COINS_REMOTE_DATA_SOURCE)
	abstract fun remoteDataSource(bind: CoinsRemoteDataSource): CoinsDataSource

	@Binds
	abstract fun cacheRepository(bind: CoinsCacheRepositoryImpl): CoinsCacheRepository

	@Binds
	abstract fun remoteRepository(bind: CoinsRemoteRepositoryImpl): CoinsRemoteRepository

	@Binds
	abstract fun coinsRepository(bind: CoinsRepositoryImpl): CoinsRepository

	@Binds
	@IntoMap
	@ViewModelKey(CoinsViewModel::class)
	abstract fun coinsViewModel(bind: CoinsViewModel): ViewModel

	@Binds
	abstract fun factory(bind: ViewModelFactory): ViewModelProvider.Factory
}