package com.azamat.nycschoolforartech.di

import com.azamat.nycschoolforartech.model.repository.remote.RemoteRepository
import com.azamat.nycschoolforartech.model.repository.remote.RemoteRepositoryImpl
import com.azamat.nycschoolforartech.model.repository.room.SchoolRoomRepository
import com.azamat.nycschoolforartech.model.repository.room.SchoolRoomRepositoryImpl
import com.azamat.nycschoolforartech.model.repository.room.ScoreRoomRepository
import com.azamat.nycschoolforartech.model.repository.room.ScoreRoomRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    factory<RemoteRepository> { RemoteRepositoryImpl(get()) }
    factory<SchoolRoomRepository> { SchoolRoomRepositoryImpl(get()) }
    factory<ScoreRoomRepository> { ScoreRoomRepositoryImpl(get()) }
}
