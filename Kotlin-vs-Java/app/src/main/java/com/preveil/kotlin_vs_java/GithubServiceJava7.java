package com.preveil.kotlin_vs_java;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.*;

public interface GithubServiceJava7 {
	String SERVICE_ENDPOINT = "https://api.github.com";

	@GET("users/{login}")
	Observable<GithubJava7> getUser(@Path("login") String login);
	//Call<GithubJava7> getUser(@Path("login") String login);
}
