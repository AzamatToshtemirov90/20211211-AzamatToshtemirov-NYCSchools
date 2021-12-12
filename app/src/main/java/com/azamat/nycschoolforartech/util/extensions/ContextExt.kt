package com.azamat.nycschoolforartech.util.extensions

import android.content.Context
import android.net.ConnectivityManager

val Context.isNetworkConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }
