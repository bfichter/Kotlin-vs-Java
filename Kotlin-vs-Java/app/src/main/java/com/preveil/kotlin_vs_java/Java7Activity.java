package com.preveil.kotlin_vs_java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.observers.DisposableObserver;

public class Java7Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		fetchGithubJava7();
		fetchGithubJava8();

		KotlinFetcher kotlinFetcher = new KotlinFetcher();
		kotlinFetcher.fetch();
	}

	private void fetchGithubJava7() {
		GithubServiceJava7 service = RetrofitFactoryJava7.createRetrofitService(GithubServiceJava7.class, GithubServiceJava7.SERVICE_ENDPOINT);
		service.getUser("google")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map(new Function<GithubJava7, String>() {
						@Override
						public String apply(GithubJava7 response) {
							return "a value mapped from Github response, with Java7 verbosity";
						}
					}
				)
				.subscribeWith(new DisposableObserver<String>() {
					@Override
					public final void onComplete() {
						Log.d("Java7", "Fetch completed");
					}

					@Override
					public final void onError(Throwable e) {
						Log.e("Java7", "ERROR " + e.getLocalizedMessage());
					}

					@Override
					public final void onNext(String response) {
						Log.d("Java7", "Response is " + response);
					}
				});
	}

	private void fetchGithubJava8() {
		GithubServiceJava7 service = RetrofitFactoryJava7.createRetrofitService(GithubServiceJava7.class, GithubServiceJava7.SERVICE_ENDPOINT);
		service.getUser("google")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.map(githubResponse -> {
					return "a value mapped from Github response, with java 8 lambdas";
				})
				.subscribe(
						response -> Log.d("Java8", response),
						error -> Log.e("Java8", error.getLocalizedMessage())
				);
	}
}
