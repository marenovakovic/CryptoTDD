package com.marko.cryptotdd.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.cryptotdd.injection.ViewModelKey
import com.marko.data.coins.*
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.injection.DI
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.injection.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.mockk.mockk
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class TestBindingHomeModule {

	@Binds
	@IntoMap
	@ViewModelKey(CoinsViewModel::class)
	abstract fun coinsViewModel(bind: CoinsViewModel): ViewModel

	@Binds
	abstract fun factory(bind: ViewModelFactory): ViewModelProvider.Factory
}

@Module(includes = [TestBindingHomeModule::class])
class TestHomeModule {

	@Singleton
	@Provides
	fun provideCoinsCacheRepository(): CoinsCacheRepository = mockk()

	@Singleton
	@Provides
	fun provideCoinsRemoteRepository(): CoinsRemoteRepository = mockk()

	@Singleton
	@Provides
	@Named(DI.COINS_CACHE_DATA_SOURCE)
	fun provideCoinsCacheDataSource(coinsCacheRepository: CoinsCacheRepository): CoinsDataSource =
		CoinsCacheDataSource(coinsCacheRepository)

	@Singleton
	@Provides
	@Named(DI.COINS_REMOTE_DATA_SOURCE)
	fun provideCoinsRemoteDataSource(coinsRemoteRepository: CoinsRemoteRepository): CoinsDataSource =
		CoinsRemoteDataSource(coinsRemoteRepository)

	@Singleton
	@Provides
	fun provideCoinsRepository(coinsDataSourceMediator: CoinsDataSourceMediator): CoinsRepository =
		CoinsRepositoryImpl(coinsDataSourceMediator)
}