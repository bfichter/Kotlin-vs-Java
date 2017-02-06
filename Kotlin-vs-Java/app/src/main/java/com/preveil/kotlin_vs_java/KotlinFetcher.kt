package com.preveil.kotlin_vs_java

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bfich on 2/6/17.
 */

class KotlinFetcher {
	fun fetch() {
		val service = RetrofitFactoryJava7.createRetrofitService(GithubServiceJava7::class.java, GithubServiceJava7.SERVICE_ENDPOINT)

		service.getUser("google")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map { response ->
					return@map "Response mapped from Github response with Kotlin"
				}
				.subscribe(
					{ response ->
						Log.d("Kotlin", response)
					},
					{ error ->
						Log.e("Kotlin", "ERROR")
					}
				)
	}
}