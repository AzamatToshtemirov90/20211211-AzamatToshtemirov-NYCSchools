package com.azamat.nycschoolforartech.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoreEntity(
    @PrimaryKey val dbn: String,
    @ColumnInfo(name = "testTakersNo") val testTakersNo: String?,
    @ColumnInfo(name = "readingAvgScore") val readingAvgScore: String?,
    @ColumnInfo(name = "mathAvgScore") val mathAvgScore: String?,
    @ColumnInfo(name = "writingAvgScore") val writingAvgScore: String?,
    @ColumnInfo(name = "schoolName") val schoolName: String?
)
