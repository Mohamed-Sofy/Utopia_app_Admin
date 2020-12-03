package com.eschima.utopia_admin.Services

import com.eschima.utopia_admin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object{
        private val BASE_URL = "http://utopials.com/api/"

        private val okBuilder = OkHttpClient.Builder()

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        // create Retrofit instance
        private var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var retrofit = builder.build()
        fun <S> createService(serviceClass: Class<S>): S {
            if (!okBuilder.interceptors().contains(logging)) {
                if (BuildConfig.DEBUG) {
                    okBuilder.addInterceptor(logging)
                }
                builder = builder.client(okBuilder.build())

                retrofit = builder.build()

            }

            return retrofit.create(serviceClass)
        }
    }

}