package com.example.shoplist.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sopovardidze
 */
data class Shop(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("address")
    val address : String,
    @SerializedName("logoUrl")
    val logoUrl : String,
    @SerializedName("backgroundUrl")
    val backgroundUrl : String,
    @SerializedName("orderNo")
    val orderNo : Int,
    @SerializedName("haveService")
    val haveService : Boolean,
    @SerializedName("estimatedTime")
    val estimatedTime : String,
    @SerializedName("deliveryFee")
    val deliveryFee : Int,
    @SerializedName("averageRating")
    val averageRating : Double,
    @SerializedName("reviewsCount")
    val reviewsCount : Int,
    @SerializedName("lowerLimit")
    val lowerLimit : Int,
    @SerializedName("channel")
    val channel : String,
    @SerializedName("isActive")
    val isActive : Boolean,
    @SerializedName("latitude")
    val latitude : Int,
    @SerializedName("longitude")
    val longitude : Int,
    @SerializedName("workingHours")
    val workingHours : List<WorkingHours>,
    @SerializedName("userPromoCode")
    val userPromoCode : String
) {
}