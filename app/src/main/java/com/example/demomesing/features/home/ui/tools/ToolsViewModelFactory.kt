package com.example.demomesing.features.home.ui.tools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.features.home.MainDataSource

class ToolsViewModelFactory(private val dataSource: MainDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ToolsViewModel(dataSource) as T
    }
}