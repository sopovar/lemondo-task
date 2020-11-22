package com.example.shoplist

import android.app.Application
import com.example.shoplist.api.RetrofitInstance
import com.example.shoplist.repository.ShopRepository
import com.example.shoplist.utils.PrefHelper
import com.example.shoplist.viewmodel.ShopViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by sopovardidze
 */
class MainApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind() from singleton { RetrofitInstance() }
        bind() from singleton { ShopRepository() }
        bind() from provider { ShopViewModelFactory(this@MainApp, instance()) }
    }

    companion object {

    }
}