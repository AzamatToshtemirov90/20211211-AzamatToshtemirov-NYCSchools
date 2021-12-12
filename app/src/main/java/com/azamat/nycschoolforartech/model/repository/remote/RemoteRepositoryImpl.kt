package com.azamat.nycschoolforartech.model.repository.remote

import com.azamat.nycschoolforartech.base.BaseApiResult
import com.azamat.nycschoolforartech.base.BaseRepository
import com.azamat.nycschoolforartech.model.remote.api.ApiService
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.remote.response.Score

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository, BaseRepository() {
    override suspend fun getSchools(): BaseApiResult<List<School>> {
        return safeApi {
            apiService.getSchools()
        }
    }

    override suspend fun getScores(): BaseApiResult<List<Score>> {
        return safeApi {
            apiService.getScores()
        }
    }

    override suspend fun getScoreByDbn(dbn: String): BaseApiResult<List<Score>> {
        return safeApi {
            apiService.getScoreByDbn(dbn)
        }
    }
}
