package com.azamat.nycschoolforartech.model.repository.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.room.dao.SchoolDao
import com.azamat.nycschoolforartech.model.room.entity.SchoolEntity

class SchoolRoomRepositoryImpl(private val schoolDao: SchoolDao) : SchoolRoomRepository {

    override val schools: LiveData<List<School>> = Transformations.map(schoolDao.getAllLiveData()) {
        val listSchool = ArrayList<School>()
        it.forEach { schoolEntity ->
            listSchool.add(
                School(
                    dbn = schoolEntity.dbn,
                    schoolName = schoolEntity.schoolName,
                    overviewParagraph  = schoolEntity.overviewParagraph,
                    location = schoolEntity.location,
                    phoneNumber = schoolEntity.phoneNumber,
                    faxNumber = schoolEntity.faxNumber,
                    schoolEmail = schoolEntity.schoolEmail,
                    website = schoolEntity.website,
                    latitude = schoolEntity.latitude,
                    longitude = schoolEntity.longitude
                )
            )
        }
        listSchool
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getAll() : List<School?>? {
        val listSchool = ArrayList<School>()
        schoolDao.getAll()?.let {
            it.forEach { schoolEntity ->
                listSchool.add(
                    School(
                        dbn = schoolEntity?.dbn ?: "",
                        schoolName = schoolEntity?.schoolName,
                        overviewParagraph  = schoolEntity?.overviewParagraph,
                        location = schoolEntity?.location,
                        phoneNumber = schoolEntity?.phoneNumber,
                        faxNumber = schoolEntity?.faxNumber,
                        schoolEmail = schoolEntity?.schoolEmail,
                        website = schoolEntity?.website,
                        latitude = schoolEntity?.latitude,
                        longitude = schoolEntity?.longitude
                    )
                )
            }
        }
        return listSchool
    }


    override suspend fun insert(school: School) : Long? =
        schoolDao.insert(
            SchoolEntity(
                dbn = school.dbn,
                schoolName = school.schoolName,
                overviewParagraph  = school.overviewParagraph,
                location = school.location,
                phoneNumber = school.phoneNumber,
                faxNumber = school.faxNumber,
                schoolEmail = school.schoolEmail,
                website = school.website,
                latitude = school.latitude,
                longitude = school.longitude
            )
        )

    override suspend fun insertAll(listSchool: List<School>) : List<Long>?  {
        val listSchoolEntity = ArrayList<SchoolEntity>()
        listSchool.forEach { school ->
            listSchoolEntity.add(
                SchoolEntity(
                    dbn = school.dbn,
                    schoolName = school.schoolName,
                    overviewParagraph  = school.overviewParagraph,
                    location = school.location,
                    phoneNumber = school.phoneNumber,
                    faxNumber = school.faxNumber,
                    schoolEmail = school.schoolEmail,
                    website = school.website,
                    latitude = school.latitude,
                    longitude = school.longitude
                )
            )
        }
        return schoolDao.insertAll(listSchoolEntity)
    }

    override suspend fun deleteAll() : Int? {
        return schoolDao.deleteAll()
    }
}
