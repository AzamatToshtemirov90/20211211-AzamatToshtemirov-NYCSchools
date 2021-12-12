package com.azamat.nycschoolforartech.model.repository.room

import android.os.Build
import com.azamat.nycschoolforartech.model.remote.response.Score
import com.azamat.nycschoolforartech.model.room.dao.ScoreDao
import com.azamat.nycschoolforartech.model.room.entity.ScoreEntity


class ScoreRoomRepositoryImpl(private val scoreDao: ScoreDao) : ScoreRoomRepository {

    override suspend fun getByDbn(dbn: String) : Score? {
        return scoreDao.getByDbn(dbn).let {
            it?.let { scoreEntity ->
                Score(
                    dbn = scoreEntity.dbn,
                    schoolName = scoreEntity.schoolName,
                    testTakersNo  = scoreEntity.testTakersNo,
                    readingAvgScore = scoreEntity.readingAvgScore,
                    mathAvgScore = scoreEntity.mathAvgScore,
                    writingAvgScore = scoreEntity.writingAvgScore
                )
            }
        }
    }

    override suspend fun getAll() : List<Score?>? {
        val listScore = ArrayList<Score>()
        scoreDao.getAll()?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.forEach { scoreEntity ->
                    listScore.add(
                        Score(
                            dbn = scoreEntity.dbn,
                            schoolName = scoreEntity.schoolName,
                            testTakersNo  = scoreEntity.testTakersNo,
                            readingAvgScore = scoreEntity.readingAvgScore,
                            mathAvgScore = scoreEntity.mathAvgScore,
                            writingAvgScore = scoreEntity.writingAvgScore
                        )
                    )
                }
            }
        }
        return listScore
    }

    override suspend fun insertAll(listScore: List<Score>): List<Long>? {
        val listScoreEntity = ArrayList<ScoreEntity>()
        listScore.forEach { score ->
            listScoreEntity.add(
                ScoreEntity(
                    dbn = score.dbn,
                    schoolName = score.schoolName,
                    testTakersNo  = score.testTakersNo,
                    readingAvgScore = score.readingAvgScore,
                    mathAvgScore = score.mathAvgScore,
                    writingAvgScore = score.writingAvgScore
                )
            )
        }
        return scoreDao.insertAll(listScoreEntity)
    }

    override suspend fun deleteAll(): Int? {
        return scoreDao.deleteAll()
    }
}
