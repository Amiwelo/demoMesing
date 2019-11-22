package com.example.demomesing.features.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.features.home.MainDataSource

class HomeViewModelFactory(private val dataSource: MainDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(dataSource) as T
    }
}