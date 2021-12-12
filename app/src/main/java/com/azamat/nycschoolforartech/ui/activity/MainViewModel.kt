package com.azamat.nycschoolforartech.ui.activity

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.azamat.nycschoolforartech.NYCSchoolsApp
import com.azamat.nycschoolforartech.base.BaseViewModel
import com.azamat.nycschoolforartech.model.enums.Status
import com.azamat.nycschoolforartech.model.remote.response.Score
import com.azamat.nycschoolforartech.model.repository.remote.RemoteRepository
import com.azamat.nycschoolforartech.model.repository.room.ScoreRoomRepository
import com.azamat.nycschoolforartech.util.extensions.isNetworkConnected
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val scoreRoomRepository: ScoreRoomRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {
    val keyword = ObservableField<String>()
    val _scoresTest = MutableLiveData<List<Score?>?>()
    val scoreTest : LiveData<List<Score?>?> = _scoresTest

    /**
     * Function to get all scores from network to ROOM
     */

    fun getAllScoresFromNetWorkToRoom() {
        viewModelScope.launch(ioDispatcher) {
            try {
                if (NYCSchoolsApp.INSTANCE.applicationContext.isNetworkConnected) {
                    remoteRepository.getScores().let { baseApiResult ->
                        when (baseApiResult.status) {
                            Status.SUCCESS -> {
                                baseApiResult.data?.let { listScore ->
                                    insertScoresToRoom(listScore)
                                }
                            }
                            Status.ERROR -> {
                                _error.postValue(baseApiResult.message)
                            }
                        }
                    }
                }
            } catch (ex: Exception) {

            } finally {

            }

        }
    }


    /**
     * Insert all list of scores into ROOM for reading offline
     */
    private fun insertScoresToRoom(scores: List<Score>) {
        viewModelScope.launch(ioDispatcher) {
            scoreRoomRepository.insertAll(scores)
        }
    }


    /**
     * Function to support unittest/integration test
     */
    fun getAllScoresFromRoom() {
        viewModelScope.launch(ioDispatcher) {
            scoreRoomRepository.getAll().let {
                _scoresTest.postValue(it)
            }
        }
    }

}
