package com.azamat.nycschoolforartech.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azamat.nycschoolforartech.model.room.entity.SchoolEntity

@Dao
interface SchoolDao {

    @Query("SELECT * from schools")
    fun getAllLiveData(): LiveData<List<SchoolEntity>>

    @Query("SELECT * from schools")
    fun getAll(): List<SchoolEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(school: SchoolEntity): Long?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(schools: List<SchoolEntity>) : List<Long>?

    @Query("DELETE FROM schools")
    suspend fun deleteAll() : Int?
}
