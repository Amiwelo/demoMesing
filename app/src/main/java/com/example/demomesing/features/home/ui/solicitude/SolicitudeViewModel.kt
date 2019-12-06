package com.example.demomesing.features.home.ui.solicitude

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.model.ResponsePregunta
import com.example.demomesing.model.ResponseSolicitude
import com.example.demomesing.model.Solicitude

class SolicitudeViewModel(private val dataSource: MainDataSource) : ViewModel() {

    private val _responseBody = MutableLiveData<List<Solicitude>>()
    val responseBody: LiveData<List<Solicitude>> = _responseBody

    fun getSolicitudesFromUser(id: Int) {
        dataSource.getSolicitudesFromUser(id, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responseBody.value = obj as List<Solicitude>
            }

            override fun onError(obj: Any?) {

            }
        })
    }
}