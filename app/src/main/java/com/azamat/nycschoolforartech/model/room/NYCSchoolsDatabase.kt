package com.azamat.nycschoolforartech.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.azamat.nycschoolforartech.model.room.dao.SchoolDao
import com.azamat.nycschoolforartech.model.room.dao.ScoreDao
import com.azamat.nycschoolforartech.model.room.entity.SchoolEntity
import com.azamat.nycschoolforartech.model.room.entity.ScoreEntity

@Database(entities = [ScoreEntity::class, SchoolEntity::class], version = 1)
abstract class NYCSchoolsDatabase : RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        const val DB_VERSION = 1
        private const val DB_NAME = "nyc_schools_database"

        @Volatile
        private var INSTANCE: NYCSchoolsDatabase? = null

        operator fun invoke(context: Context): NYCSchoolsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NYCSchoolsDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

    }
}
