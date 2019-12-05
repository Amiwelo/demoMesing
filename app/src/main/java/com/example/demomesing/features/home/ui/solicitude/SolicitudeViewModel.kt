package com.example.demomesing.features.home.ui.solicitude

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.features.home.MainDataSource

class SolicitudeViewModel(private val dataSource: MainDataSource) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text


}