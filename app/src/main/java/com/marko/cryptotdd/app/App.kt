package com.marko.cryptotdd.app

import com.marko.cryptotdd.injection.application.ApplicationComponent
import com.marko.cryptotdd.injection.application.createComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component

	private val component: ApplicationComponent = createComponent(this)
}