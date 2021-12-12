package com.azamat.nycschoolforartech.model.room.dao

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
    fun getByDbn(dbn: String): ScoreEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(score: ScoreEntity): Long?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAll(scores: List<ScoreEntity>) : List<Long>?

    @Query("DELETE FROM scores")
     fun deleteAll() : Int?
}

