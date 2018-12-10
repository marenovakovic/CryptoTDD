package com.marko.cryptotdd.injection.application

import android.app.Application
import android.content.Context
import com.marko.cryptotdd.app.App
import com.marko.cryptotdd.app.ApplicationModule
import com.marko.cryptotdd.home.HomeModule
import com.marko.cryptotdd.injection.activity.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		ApplicationModule::class,
		ActivityBindingModule::class,
		HomeModule::class
	]
)
interface ApplicationComponent : AndroidInjector<App>

fun Application.createComponent(context: Context): ApplicationComponent =
	DaggerApplicationComponent
		.builder()
		.applicationModule(ApplicationModule(context))
		.build()