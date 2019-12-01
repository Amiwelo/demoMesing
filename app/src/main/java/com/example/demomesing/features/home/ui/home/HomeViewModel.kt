package com.example.demomesing.features.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.model.Categoria
import com.example.demomesing.model.Servicios
import com.google.gson.Gson

class HomeViewModel(private val dataSource: MainDataSource) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _responseCat = MutableLiveData<List<Categoria>>()
    val responseCat: LiveData<List<Categoria>> = _responseCat

    private var _listService = MutableLiveData<List<Servicios>>()
    val listService: LiveData<List<Servicios>> = _listService

    private val _realice = MutableLiveData<Boolean>()
    val realice: LiveData<Boolean> = _realice

    fun getServices(idSer: Int) {
        dataSource.getServices(idSer, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _listService.value = obj as List<Servicios>
                _realice.value = true
            }

            override fun onError(obj: Any?) {
                _text.value = obj as String
                _realice.value = false
            }

        })
    }

    val gson = Gson()
    fun getCat() {
        dataSource.getCategorias(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responseCat.value = obj as List<Categoria>
                _realice.value = true

            }

            override fun onError(obj: Any?) {
                _text.value = obj as String
                _realice.value = false
            }

        })
    }
}