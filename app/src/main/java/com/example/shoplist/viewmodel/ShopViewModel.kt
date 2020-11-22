package com.example.shoplist.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoplist.MainApp
import com.example.shoplist.model.AuthToken
import com.example.shoplist.model.Resource
import com.example.shoplist.model.ShopResponse
import com.example.shoplist.repository.ShopRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

/**
 * Created by sopovardidze
 */
class ShopViewModel(
    app: Application,
    private val repo: ShopRepository
) : AndroidViewModel(app) {

    val authToken: MutableLiveData<Resource<AuthToken>> = MutableLiveData()

    val shopData: MutableLiveData<Resource<ShopResponse>> = MutableLiveData()

    init {
        getAuthToken()
    }

    private fun getAuthToken() = viewModelScope.launch {
        safeAuthCall()
    }

    fun getShopData() = viewModelScope.launch {
        safeShopCall()
    }

    private fun handleAuthResponse(response: Response<AuthToken>): Resource<AuthToken> {
        if (response.isSuccessful) {
            response.body().let {
                return Resource.Success(it!!)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleShopResponse(response: Response<ShopResponse>): Resource<ShopResponse> {
        if (response.isSuccessful) {
            response.body().let {
                return Resource.Success(it!!)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeAuthCall() {
        authToken.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repo.getAuthToken()
                authToken.postValue(handleAuthResponse(response))
            } else {
                authToken.postValue(Resource.Error("No Internet Connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> authToken.postValue(Resource.Error("Network Failure"))
                else -> authToken.postValue(Resource.Error("Unknown Error"))
            }
        }
    }

    private suspend fun safeShopCall() {
        shopData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repo.getShops()
                shopData.postValue(handleShopResponse(response))
            } else {
                shopData.postValue(Resource.Error("No Internet Connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> shopData.postValue(Resource.Error("Network Failure"))
                else -> shopData.postValue(Resource.Error("Unknown Error"))
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MainApp>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}