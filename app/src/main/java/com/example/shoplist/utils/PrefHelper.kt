package com.example.shoplist.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.shoplist.utils.Constants.Companion.PREFS_NAME

/**
 * Created by sopovardidze
 */
object PrefHelper {

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun write(key: String, value: String?) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            commit()
        }
    }

    fun read(key: String, value: String): String? {
        return prefs.getString(key, value)
    }
}