package com.marko.cryptotdd.factory

import java.util.*

object BaseFactory {

	val int = Math.random().toInt()

	val string = UUID.randomUUID().toString()
}