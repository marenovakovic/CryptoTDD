package com.marko.cryptotdd.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.marko.cryptotdd.app.TestApp

class CryptoTestRunner : AndroidJUnitRunner() {

	override fun newApplication(
		cl: ClassLoader?,
		className: String?,
		context: Context?
	): Application = super.newApplication(cl, TestApp::class.java.name, context)
}