package com.example.demomesing.features.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.model.ResponseService
import com.example.demomesing.model.Servicios
import com.google.gson.Gson
import com.google.gson.JsonObject

class HomeViewModel(private val dataSource: MainDataSource) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _listService = MutableLiveData<List<Servicios>>()
    val listServicios: LiveData<List<Servicios>> = _listService

    fun getServices(idServ: Int) {
        dataSource.getServices(idServ, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                //val json = obj as JsonObject
                //Log.i("JSON", "${json}")
                _listService.value = obj as List<Servicios>
                Log.i("TAG", "${_listService.value}")
            }

            override fun onError(obj: Any?) {
                _text.value = obj as String
            }

        })
    }
}