package com.azamat.nycschoolforartech.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _error: MutableLiveData<String?> = MutableLiveData()
    val error: LiveData<String?> get() = _error
}
