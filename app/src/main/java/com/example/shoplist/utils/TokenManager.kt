package com.example.shoplist.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(context: Context) {

    private val dataStore = context.createDataStore(name = "auth_token_prefs")

    companion object {
        val AUTH_TOKEN_KEY = preferencesKey<String>("AUTH_TOKEN")
    }

    suspend fun storeUser(name: String) {
        dataStore.edit {
            it[AUTH_TOKEN_KEY] = name
        }
    }

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[AUTH_TOKEN_KEY] ?: ""
    }
}