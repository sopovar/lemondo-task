package com.example.shoplist.api

import com.example.shoplist.MainApp
import com.example.shoplist.ui.activity.MainActivity
import com.example.shoplist.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sopovardidze
 */
class RetrofitInstance {

    companion object {
        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(AddSessionInterceptor())
//                .addInterceptor { chain ->
//                    val request = chain.request()
//                    val response = chain.proceed(request)
//                    if (response.code == 401) {
//
//                    }
//                    response
//                }
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ShopApi::class.java)
        }
    }
}