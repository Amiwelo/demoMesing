package com.example.demomesing.features.solicitud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.data.session.ShPreference

class PreguntaViewModelFactory(private val dataSource: PreguntaDataSource, private val shPreference: ShPreference) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PreguntaViewModel(dataSource, shPreference) as T
    }
}