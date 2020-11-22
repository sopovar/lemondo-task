package com.example.shoplist.repository

import com.example.shoplist.api.RetrofitInstance
import com.example.shoplist.model.AuthToken
import retrofit2.Response

/**
 * Created by sopovardidze
 */
class ShopRepository {

    suspend fun getAuthToken() =
            RetrofitInstance.api.getAuthToken("Moitane", "kjkszpj", "client_credentials", "MoitaneApi")

    suspend fun getShops() = RetrofitInstance.api.getShops()

}