package com.example.demomesing.features.solicitud

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.di.Injection
import com.example.demomesing.model.Pregunta
import com.example.demomesing.model.Pregunta2
import com.example.demomesing.model.Pregunta3
import kotlinx.android.synthetic.main.activity_pregunta.*

class PreguntaActivity : AppCompatActivity() {
    lateinit var  viewModel: PreguntaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta)
        initApp()
    }
    private fun initApp(){
        viewModel = ViewModelProviders.of(
            this,
            PreguntaViewModelFactory(
                Injection.getPregunta(),
                ShPreference(
                    getSharedPreferences(
                        ShPreference.PREFERENCE_NAME,
                        Context.MODE_PRIVATE
                    ), this
                )
            )
        ).get(PreguntaViewModel::class.java)
        viewModel.getListPreguntas2()
        viewModel.getListPreguntas3()
        viewModel.getListPreguntas4()

        viewModel.responsePreguntas2.observe(this, pregunta2)
        viewModel.responsePreguntas3.observe(this, pregunta3)
        viewModel.responsePreguntas4.observe(this, pregunta4)
    }
    private val pregunta2 = Observer<List<Pregunta>>{
        spinnerAdapterPregunta2(it)
    }
    private val pregunta3 = Observer<List<Pregunta>>{
        spinnerAdapterPregunta3(it)
    }
    private val pregunta4 = Observer<List<Pregunta>>{
        spinnerAdapterPregunta4(it)
    }

    private fun spinnerAdapterPregunta2(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion2.adapter = arrayAdapter
    }
    private fun spinnerAdapterPregunta3(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion3.adapter = arrayAdapter
    }
    private fun spinnerAdapterPregunta4(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion4.adapter = arrayAdapter
    }

}
