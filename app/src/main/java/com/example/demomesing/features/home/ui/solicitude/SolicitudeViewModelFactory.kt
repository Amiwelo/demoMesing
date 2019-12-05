package com.example.demomesing.features.home.ui.solicitude

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.features.home.MainDataSource

class SolicitudeViewModelFactory(private val dataSource: MainDataSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SolicitudeViewModel(dataSource) as T
    }

}
