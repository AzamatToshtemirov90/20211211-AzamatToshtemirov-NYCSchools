package com.azamat.nycschoolforartech.ui.fragment.scores

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.azamat.nycschoolforartech.base.BaseViewModel
import com.azamat.nycschoolforartech.model.enums.Status
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.remote.response.Score
import com.azamat.nycschoolforartech.model.repository.remote.RemoteRepository
import com.azamat.nycschoolforartech.model.repository.room.ScoreRoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ScoreViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val scoreRoomRepository: ScoreRoomRepository,
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    val school = ObservableField<School>()
    val score = ObservableField<Score>(Score.empty())


    /**
     * List of all scores was downloaded from MainActivity, so this function will call ROOMDB to get the data it wants, then make a network call
     * to get the latest one from network and check if those data are different, if they are different, [score] will be updated with the latest data from network
     * and show it to the UI by DataBinding
     */
    fun getScoreByDbnFromRoom(dbn: String) {
        viewModelScope.launch(ioDispatcher) {
            scoreRoomRepository.getByDbn(dbn).let {
                score.set(it)
                getScoreByDbnFromNetwork(dbn)
            }
        }
    }

    /**
     * Function to get score from network and compare its returned value with the one from RoomDM, if they are different, UI will show the data from network
     */
    private fun getScoreByDbnFromNetwork(dbn: String) {
        viewModelScope.launch(ioDispatcher) {
            remoteRepository.getScoreByDbn(dbn).let { baseApiResult ->
                when (baseApiResult.status) {
                    Status.SUCCESS -> {
                        baseApiResult.data?.let { scoreRemote ->
                            if (!scoreRemote.isNullOrEmpty() && scoreRemote[0] != score.get()) {
                                score.set(scoreRemote[0])
                            }
                        }
                    }
                    Status.ERROR -> {
                        _error.postValue(baseApiResult.message)
                    }
                }

            }
        }
    }
}
