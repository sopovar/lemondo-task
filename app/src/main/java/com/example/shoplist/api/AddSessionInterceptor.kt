package com.example.shoplist.api

import com.example.shoplist.MainApp
import com.example.shoplist.utils.Constants.Companion.TOKEN
import com.example.shoplist.utils.PrefHelper
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by sopovardidze
 */
class AddSessionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val token = PrefHelper.read(TOKEN, "")
        builder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(builder.build())
    }
}