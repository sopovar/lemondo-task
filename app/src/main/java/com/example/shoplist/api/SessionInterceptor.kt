package com.example.shoplist.api

import com.example.shoplist.viewmodel.ShopViewModel
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by sopovardidze
 */
class SessionInterceptor(
    private val viewModel: ShopViewModel
)  : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if(response.code == 401){
           
        }
        return response
    }


}