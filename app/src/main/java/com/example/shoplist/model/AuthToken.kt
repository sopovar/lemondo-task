package com.example.shoplist.model

import android.util.Log
import com.google.gson.annotations.SerializedName

/**
 * Created by sopovardidze
 */
data class AuthToken(
    @SerializedName("access_token")
    val access_token : String,
    @SerializedName("expires_in")
    val expires_in : Long
) {
}