package com.marko.domain.time

import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TimeTest {

	@Test
	fun `check now value`() {
		assert(now == System.currentTimeMillis())
	}
}