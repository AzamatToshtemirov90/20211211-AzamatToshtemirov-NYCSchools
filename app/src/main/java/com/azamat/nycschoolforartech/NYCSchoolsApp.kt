package com.azamat.nycschoolforartech

import android.app.Application
import com.azamat.nycschoolforartech.di.*
import com.azamat.nycschoolforartech.util.SharedPreferences.initSharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NYCSchoolsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initSharedPreferences(this.applicationContext)

        startKoin{
//            androidLogger()
            androidContext(this@NYCSchoolsApp)
            modules(listOf(viewModelsModule, networkModule, repositoryModule, apiModule, roomModule))
        }
    }

    companion object {
        lateinit var INSTANCE: NYCSchoolsApp
    }
}
