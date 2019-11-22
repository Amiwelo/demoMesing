package com.example.demomesing.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.data.session.ShPreference

class MainViewModelFactory(private val dataSource: MainDataSource, private val shPreference: ShPreference): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataSource, shPreference) as T
    }

}