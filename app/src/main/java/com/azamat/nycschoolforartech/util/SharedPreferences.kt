package com.azamat.nycschoolforartech.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.azamat.nycschoolforartech.model.enums.SharedPreferenceKey

object SharedPreferences {

    lateinit var sharedPreferences: SharedPreferences

    fun initSharedPreferences(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getString(key: SharedPreferenceKey) = sharedPreferences.getString(key.name, null)
    fun putString(key: SharedPreferenceKey, value: String?) =
        sharedPreferences.edit().putString(key.name, value).apply()
}
