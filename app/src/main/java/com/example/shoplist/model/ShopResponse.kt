package com.example.shoplist.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sopovardidze
 */
data class ShopResponse(
    @SerializedName("shops")
    val shops : List<Shop>,
    @SerializedName("httpStatusCode")
    val httpStatusCode : Int,
    @SerializedName("userMessage")
    val userMessage : String,
    @SerializedName("developerMessage")
    val developerMessage : String,
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("errors")
    val errors : List<String>
) {
}