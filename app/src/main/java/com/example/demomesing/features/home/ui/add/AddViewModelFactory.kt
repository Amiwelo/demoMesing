package com.example.demomesing.features.home.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.features.home.MainDataSource

class AddViewModelFactory(private val dataSource: MainDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddViewModel(dataSource) as T
    }
}