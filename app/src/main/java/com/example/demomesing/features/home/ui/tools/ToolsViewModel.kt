package com.example.demomesing.features.home.ui.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.model.ResponseUsuario

class ToolsViewModel(private val dataSource: MainDataSource) : ViewModel() {

    private val _msj = MutableLiveData<ResponseUsuario>()
    val msj: LiveData<ResponseUsuario> = _msj

    fun createUser(name: String?, lastNamePat: String?, lastNameMat: String?, email: String?, userName: String?, pwd: String?) {
        dataSource.createUser(
        name,
        lastNamePat,
        lastNameMat,
        email,
        userName,
        pwd,
            object : ObjectOperation {
                override fun onSuccess(obj: Any?) {
                    _msj.value = obj as ResponseUsuario
                }

                override fun onError(obj: Any?) {
                    _msj.value = obj as ResponseUsuario
                }

            }
        )
    }


}