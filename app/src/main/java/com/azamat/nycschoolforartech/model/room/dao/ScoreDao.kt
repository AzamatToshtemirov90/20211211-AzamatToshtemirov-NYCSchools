package com.azamat.nycschoolforartech.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azamat.nycschoolforartech.model.room.entity.ScoreEntity

@Dao
interface ScoreDao {

    @Query("SELECT * from scores")
    fun getAll(): List<ScoreEntity>?

    @Query("SELECT * from scores Where dbn == :dbn")
    suspend fun getByDbn(dbn: String): ScoreEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: ScoreEntity): Long?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(scores: List<ScoreEntity>) : List<Long>?

    @Query("DELETE FROM scores")
    suspend fun deleteAll() : Int?
}

