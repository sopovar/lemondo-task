package com.example.shoplist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.repository.ShopRepository

/**
 * Created by sopovardidze
 */
class ShopViewModelFactory(
    val app: Application,
    private val shopRepository: ShopRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopViewModel(app, shopRepository) as T
    }
}