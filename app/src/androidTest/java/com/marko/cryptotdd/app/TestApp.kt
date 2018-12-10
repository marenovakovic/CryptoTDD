package com.marko.cryptotdd.app

import com.marko.cryptotdd.injection.createTestComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TestApp : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component

	val component = createTestComponent()
}