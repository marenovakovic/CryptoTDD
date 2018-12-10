package com.marko.cryptotdd.injection.activity

import com.marko.cryptotdd.home.HomeModule
import com.marko.cryptotdd.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ContributesAndroidInjector(modules = [HomeModule::class])
	abstract fun main(): MainActivity
}