package com.azamat.nycschoolforartech.di

import com.azamat.nycschoolforartech.model.room.NYCSchoolsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        NYCSchoolsDatabase(androidApplication())
    }
    single(createdAtStart = false) { get<NYCSchoolsDatabase>().schoolDao() }
    single(createdAtStart = false) { get<NYCSchoolsDatabase>().scoreDao() }
}
