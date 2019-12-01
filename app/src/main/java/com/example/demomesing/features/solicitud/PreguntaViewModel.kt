package com.example.demomesing.features.solicitud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.model.Pregunta
import com.example.demomesing.model.ResponsePregunta

class PreguntaViewModel(private val dataSource: PreguntaDataSource, private val shPreference: ShPreference): ViewModel() {

    private val _responseBody = MutableLiveData<ResponsePregunta>()
    val responseBody: LiveData<ResponsePregunta> = _responseBody

    private val _responsePreguntas2 = MutableLiveData<List<Pregunta>>()
    val responsePreguntas: LiveData<List<Pregunta>> = _responsePreguntas2

    fun createSolicitude(p1: Int, p2: Int, p3: Int, cel: String, email: String, idOfer: Int){
        //Log.i("TAG USU", "${shPreference.user!!.id}")
        dataSource.createSolicitude(p1, p2, p3, cel, email, shPreference.user!!.id, idOfer, object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responseBody.value = obj as ResponsePregunta
            }

            override fun onError(obj: Any?) {
                _responseBody.value = obj as ResponsePregunta
            }

        })
    }

    fun getListPreguntas(){
        dataSource.getPreguntas(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responsePreguntas2.value = obj as List<Pregunta>
            }

            override fun onError(obj: Any?) {

            }

        })
    }
}