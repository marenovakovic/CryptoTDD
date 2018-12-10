package com.marko.cryptotdd.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes res: Int): View =
	LayoutInflater.from(context).inflate(res, this, false)