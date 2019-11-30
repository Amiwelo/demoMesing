package com.example.demomesing.features.solicitud

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.di.Injection
import com.example.demomesing.model.Pregunta
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_pregunta.*

class PreguntaActivity : AppCompatActivity() {
    lateinit var  viewModel: PreguntaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta)
        initApp()

        btn_registrar_pregunta.setOnClickListener {
            val idOfer = 0
            val data = intent.getIntExtra("idOfer", idOfer)
            viewModel.createSolicitude(1,1,1, et_numero_contacto.text.toString().toInt(), et_correo.text.toString(), idOfer)
        }
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
        viewModel.getListPreguntas()

        viewModel.responsePreguntas.observe(this, pregunta)
    }
    private val pregunta = Observer<List<Pregunta>>{
        val respuesta1 = it.filter { key -> key.idGruPreg==1 }
        val respuesta2 = it.filter { key-> key.idGruPreg==2 }
        val respuesta3 = it.filter { key-> key.idGruPreg==3 }
        spinnerAdapterPregunta1(respuesta1)
        spinnerAdapterPregunta2(respuesta2)
        spinnerAdapterPregunta3(respuesta3)
    }


    private fun spinnerAdapterPregunta1(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion2.adapter = arrayAdapter
    }
    private fun spinnerAdapterPregunta2(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion3.adapter = arrayAdapter
    }
    private fun spinnerAdapterPregunta3(listPregunta: List<Pregunta>){
        val arrayAdapter: ArrayAdapter<Pregunta> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPregunta)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion4.adapter = arrayAdapter
    }

}
