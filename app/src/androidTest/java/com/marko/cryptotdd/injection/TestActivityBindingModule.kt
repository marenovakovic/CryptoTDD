package com.marko.cryptotdd.injection

import com.marko.cryptotdd.home.MainActivity
import com.marko.cryptotdd.home.TestHomeModule
import com.marko.cryptotdd.injection.activity.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [TestHomeModule::class])
	abstract fun mainActivity(): MainActivity
}