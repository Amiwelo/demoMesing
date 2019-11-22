package com.example.demomesing.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.model.Collection

class MainViewModel (private val dataSource: MainDataSource, private val shPreference: ShPreference):ViewModel() {

    private var _responseBody = MutableLiveData<Collection>()
    var responseBody : LiveData<Collection> = _responseBody

    fun launchMain(Option: Int, IdRol: Int, IdPer: Int) {
        dataSource.launchMain(Option, IdRol, IdPer, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responseBody.value = obj as Collection
            }

            override fun onError(obj: Any?) {
                Log.e("error", "Valimos")
            }

        })
    }
}