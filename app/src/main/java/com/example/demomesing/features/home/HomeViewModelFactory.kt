package com.example.demomesing.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.data.session.ShPreference

class HomeViewModelFactory(private val dataSource: HomeDataSource, private val shPreference: ShPreference): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(dataSource, shPreference) as T
    }

}