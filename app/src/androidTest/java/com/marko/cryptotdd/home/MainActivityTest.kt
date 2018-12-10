package com.marko.cryptotdd.home

import androidx.lifecycle.ViewModelProviders
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.injection.ViewModelFactory
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

	@get: Rule
	val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

	private val context = InstrumentationRegistry.getInstrumentation().targetContext

	@Inject
	lateinit var factory: ViewModelFactory

	private val viewModel: CoinsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(rule.activity, factory).get(CoinsViewModel::class.java)
	}

	@Test
	fun callFetchOnViewModel() {
		verify(exactly = 1) { viewModel.fetch() }
	}
}
