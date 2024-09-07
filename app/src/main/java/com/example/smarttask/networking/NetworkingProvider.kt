package com.example.smarttask.networking

import android.content.Context
import com.example.smarttask.app.MyApp
import com.example.smarttask.cache.getToken
import com.example.smarttask.utility.Constants

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

var appInterceptor:Interceptor?=null
fun provideHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    if (appInterceptor == null)
        appInterceptor = MyAppInterceptor(MyApp.appContext!!)

    httpClient.addInterceptor(appInterceptor!!)
    httpClient.addInterceptor(logging)
    return httpClient.build()
}

fun provideApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

class MyAppInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        val headers = request.headers.newBuilder()
            .add("Content-Type", "application/json")
            .add("Accept", "application/json")
            .add(
                "Authorization",
                "Bearer ${getToken(context)}"
            )
            .build()

        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)
    }
}