package com.azamat.nycschoolforartech.model.repository.room

import androidx.lifecycle.LiveData
import com.azamat.nycschoolforartech.model.remote.response.School

interface SchoolRoomRepository {
    val schools: LiveData<List<School>>

    suspend fun getAll() : List<School?>?

    suspend fun insert(school: School) : Long?

    suspend fun insertAll(listSchool: List<School>) : List<Long>?

    suspend fun deleteAll() : Int?

}
