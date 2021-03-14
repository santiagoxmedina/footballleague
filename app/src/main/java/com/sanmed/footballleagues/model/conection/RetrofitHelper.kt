package com.sanmed.footballleagues.model.conection

import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {
    companion object {
        fun getRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val dispatcher = Dispatcher()
            dispatcher.maxRequests = 1
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.readTimeout(204, TimeUnit.SECONDS)
            okHttpClientBuilder.writeTimeout(204, TimeUnit.SECONDS)
            okHttpClientBuilder.connectTimeout(204, TimeUnit.SECONDS)
            okHttpClientBuilder.dispatcher(dispatcher)
            getLogInterceptor()?.let { okHttpClientBuilder.addInterceptor(it) }

            return okHttpClientBuilder.build()
        }

        private fun getLogInterceptor(): Interceptor? {
            val interceptor = HttpLoggingInterceptor()
            //interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return interceptor
        }

    }


}