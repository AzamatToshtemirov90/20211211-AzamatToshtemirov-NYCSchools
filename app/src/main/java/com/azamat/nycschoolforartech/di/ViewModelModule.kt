package com.azamat.nycschoolforartech.di

import com.azamat.nycschoolforartech.ui.activity.MainViewModel
import com.azamat.nycschoolforartech.ui.fragment.schoollist.SchoolsListViewModel
import com.azamat.nycschoolforartech.ui.fragment.scores.ScoreViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    factory { Dispatchers.IO }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { SchoolsListViewModel(get(),get(), get()) }
    viewModel { ScoreViewModel(get(), get(), get()) }
}
