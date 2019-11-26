package com.example.demomesing.features.solicitud

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demomesing.data.ObjectOperation
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.model.Pregunta
import com.example.demomesing.model.Pregunta2
import com.example.demomesing.model.Pregunta3

class PreguntaViewModel(private val dataSource: PreguntaDataSource, private val shPreference: ShPreference): ViewModel() {

    private val _responseBody = MutableLiveData<String>()
    val responseBody: LiveData<String> = _responseBody

    private val _responsePreguntas2 = MutableLiveData<List<Pregunta>>()
    val responsePreguntas2: LiveData<List<Pregunta>> = _responsePreguntas2

    private val _responsePreguntas3 = MutableLiveData<List<Pregunta>>()
    val responsePreguntas3: LiveData<List<Pregunta>> = _responsePreguntas3

    private val _responsePreguntas4 = MutableLiveData<List<Pregunta>>()
    val responsePreguntas4: LiveData<List<Pregunta>> = _responsePreguntas4

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

    fun getListPreguntas2(){
        dataSource.getPreguntas2(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responsePreguntas2.value = obj as List<Pregunta>
            }

            override fun onError(obj: Any?) {

            }

        })
    }

    fun getListPreguntas3(){
        dataSource.getPreguntas3(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responsePreguntas3.value = obj as List<Pregunta>
            }

            override fun onError(obj: Any?) {

            }

        })
    }

    fun getListPreguntas4(){
        dataSource.getPreguntas4(object : ObjectOperation {
            override fun onSuccess(obj: Any?) {
                _responsePreguntas4.value = obj as List<Pregunta>
            }

            override fun onError(obj: Any?) {

            }

        })
    }
}