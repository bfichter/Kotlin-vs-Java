package com.preveil.kotlin_vs_java;

import retrofit2.Retrofit;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bfich on 2/3/17.
 */

public class RetrofitFactoryJava7 {
	static <T> T createRetrofitService(final Class<T> serviceClass, final String endPoint) {
		final Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(endPoint)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		T service = retrofit.create(serviceClass);

		return service;
	}
}
