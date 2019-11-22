package com.example.demomesing.features.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.features.home.MainDataSource

class HomeViewModel (private val dataSource: MainDataSource, private val shPreference: ShPreference):ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    fun getServices(idServ: Int){
        dataSource.getServices(idServ, object: ObjectOperation{
            override fun onSuccess(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}