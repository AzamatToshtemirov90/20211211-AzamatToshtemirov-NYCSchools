package com.azamat.nycschoolforartech.ui.fragment.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.azamat.nycschoolforartech.base.BaseViewModel
import com.azamat.nycschoolforartech.model.enums.Status
import com.azamat.nycschoolforartech.model.remote.response.School
import com.azamat.nycschoolforartech.model.repository.remote.RemoteRepository
import com.azamat.nycschoolforartech.model.repository.room.SchoolRoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class SchoolsListViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val schoolRoomRepository: SchoolRoomRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    var schools: LiveData<List<School>> = schoolRoomRepository.schools

    val _schoolListTest = MutableLiveData<List<School?>?>()
    val schoolListTest : LiveData<List<School?>?> = _schoolListTest

    /**
     * it always gets data from Room DB first, then make a network call and compare the value from network with RoomDB,
     * if they are different, update the UI with latest data from network, otherwise, do nothing
     */
    fun getSchoolListFromRoom() {
        viewModelScope.launch(ioDispatcher) {
            try {
                schoolRoomRepository.schools.let {
                    it.value?.let { _ ->
                        schools = it
                    }
                    getSchoolListFromNetwork(it.value)
                }
            } catch (ex: Exception) {

            } finally {

            }
        }
    }

    /**
     * Function calling network to get list of schools, if the list of school getting from network is different from that getting from ROOM DB,
     * it will insert the latest list getting from network into RoomDB, after that [schools] will observe the data changed in RoomDB, and auto update
     * the change to the RecyclerView based on DataBinding
     */
    private fun getSchoolListFromNetwork(schoolsFromRoom: List<School>?) {
        viewModelScope.launch(ioDispatcher) {
            remoteRepository.getSchools().let { baseApiResult ->
                when (baseApiResult.status) {
                    Status.SUCCESS -> {
                        baseApiResult.data?.let { listSchoolNetwork ->
                            if (schoolsFromRoom == null || !listSchoolNetwork.containsAll(schoolsFromRoom.toImmutableList()) || !schoolsFromRoom.toImmutableList().containsAll(listSchoolNetwork)) {
                                insertSchoolsToRoom(listSchoolNetwork)
                            }
                        }
                    }
                    Status.ERROR -> {
                        //_error.postValue(baseApiResult.message)
                    }
                }

            }
        }
    }

    /**
     * Insert all list of schools into ROOM for reading offline
     */
    private fun insertSchoolsToRoom(schools: List<School>) {
        viewModelScope.launch(ioDispatcher) {
            schoolRoomRepository.insertAll(schools)
        }
    }

    /**
     * Function to support Unit/Integration Test
     */
    fun getAllSchoolsFromRoom() {
        viewModelScope.launch(ioDispatcher) {
            schoolRoomRepository.getAll().let {
                _schoolListTest.postValue(it)
            }
        }
    }
}

