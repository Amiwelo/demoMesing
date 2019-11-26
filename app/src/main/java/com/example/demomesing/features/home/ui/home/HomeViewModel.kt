package com.example.demomesing.features.home.ui.home

import android.util.Log
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

    private val _responseCategorias = MutableLiveData<List<Categoria>>()
    val responseCategorias: LiveData<List<Categoria>> = _responseCategorias

    private var _listService = MutableLiveData<List<Servicios>>()
    val listServicios: LiveData<List<Servicios>> = _listService

    fun getServices(idServ: Int) {
        dataSource.getServices(idServ, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _listService.value = obj as List<Servicios>
                Log.i("TAG", "${_listService.value}")
            }

            override fun onError(obj: Any?) {
                _text.value = obj as String
            }

        })
    }
    val gson = Gson()
    fun getCategorias(){
        dataSource.getCategorias(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                Log.i("RESPONSE", "$obj")
                _responseCategorias.value = obj as List<Categoria>

            }

            override fun onError(obj: Any?) {
                _text.value = obj as String
            }

        })
    }
}