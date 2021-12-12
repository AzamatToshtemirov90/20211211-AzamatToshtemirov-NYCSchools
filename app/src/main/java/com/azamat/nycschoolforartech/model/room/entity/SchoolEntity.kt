package com.azamat.nycschoolforartech.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schools")
data class SchoolEntity(
    @PrimaryKey val dbn: String,
    @ColumnInfo(name = "schoolName")  val schoolName: String?,
    @ColumnInfo(name = "overviewParagraph")  val overviewParagraph: String?,
    @ColumnInfo(name = "location")  val location: String?,
    @ColumnInfo(name = "phoneNumber")  val phoneNumber: String?,
    @ColumnInfo(name = "faxNumber")  val faxNumber: String?,
    @ColumnInfo(name = "schoolEmail")  val schoolEmail: String?,
    @ColumnInfo(name = "website")  val website: String?,
    @ColumnInfo(name = "latitude")  val latitude: String?,
    @ColumnInfo(name = "longitude")  val longitude: String?,
)
