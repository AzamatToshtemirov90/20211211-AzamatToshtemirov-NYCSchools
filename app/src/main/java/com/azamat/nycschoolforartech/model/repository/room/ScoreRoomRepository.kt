package com.azamat.nycschoolforartech.model.repository.room

import com.azamat.nycschoolforartech.model.remote.response.Score

interface ScoreRoomRepository {

    suspend fun getByDbn(dbn: String) : Score?

    suspend fun insertAll(listScore: List<Score>) : List<Long>?

    suspend fun deleteAll() : Int?

    suspend fun getAll(): List<Score?>?
}
