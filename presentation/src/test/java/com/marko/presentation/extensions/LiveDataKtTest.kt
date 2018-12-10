package com.marko.presentation.extensions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LiveDataKtTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	@Test
	fun `check does observer gets notified when data changes`() {

	}
}