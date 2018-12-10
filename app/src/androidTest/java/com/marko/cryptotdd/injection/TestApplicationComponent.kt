package com.marko.cryptotdd.injection

import com.marko.cryptotdd.app.TestApp
import com.marko.cryptotdd.app.TestApplicationModule
import com.marko.cryptotdd.home.TestHomeModule
import com.marko.cryptotdd.injection.application.ApplicationComponent
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		TestApplicationModule::class,
		TestHomeModule::class,
		TestActivityBindingModule::class
	]
)
interface TestApplicationComponent : ApplicationComponent

fun TestApp.createTestComponent(): TestApplicationComponent =
	DaggerTestApplicationComponent.create()