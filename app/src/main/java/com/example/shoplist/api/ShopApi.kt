package com.example.shoplist.api

import com.example.shoplist.model.AuthToken
import com.example.shoplist.model.ShopResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by sopovardidze
 */
interface ShopApi {

    @POST("connect/token")
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field("client_id")
        client_id : String,
        @Field("client_secret")
        client_secret : String,
        @Field("grant_type")
        grant_type : String,
        @Field("audience")
        audience : String
    ) : Response<AuthToken>

    @GET("v1/Shops")
    suspend fun getShops() : Response<ShopResponse>
}