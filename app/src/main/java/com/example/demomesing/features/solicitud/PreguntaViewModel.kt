package com.example.demomesing.features.solicitud

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference

class PreguntaViewModel(private val dataSource: PreguntaDataSource, private val shPreference: ShPreference): ViewModel() {

    private val _responseBody = MutableLiveData<String>()
    val responseBody: LiveData<String> = _responseBody

    fun createSolicitude(p1: Int, p2: Int, p3: Int, cel: Int, email: String, id: Int){
        dataSource.createSolicitude(p1, p2, p3, cel, email, id, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}