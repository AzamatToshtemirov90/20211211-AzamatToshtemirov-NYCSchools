package com.azamat.nycschoolforartech.model.remote.api

import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.remote.response.Score
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): List<School>

    @GET("f9bf-2cp4.json")
    suspend fun getScores(): List<Score>

    @GET("f9bf-2cp4.json")
    suspend fun getScoreByDbn(@Query("dbn") dbn: String): List<Score>
}
