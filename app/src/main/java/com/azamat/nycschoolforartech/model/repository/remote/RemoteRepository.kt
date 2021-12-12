package com.azamat.nycschoolforartech.model.repository.remote

import com.azamat.nycschoolforartech.base.BaseApiResult
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.remote.response.Score

interface RemoteRepository {

    suspend fun getSchools() : BaseApiResult<List<School>>

    suspend fun getScores() : BaseApiResult<List<Score>>

    suspend fun getScoreByDbn(dbn: String) : BaseApiResult<List<Score>>
}
