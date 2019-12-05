package com.example.demomesing.features.solicitud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.data.session.ShPreference
import com.example.demomesing.data.session.ShPreference.Companion.PREFERENCE_NAME
import com.example.demomesing.di.Injection
import com.example.demomesing.features.home.HomeActivity
import com.example.demomesing.model.Pregunta
import com.example.demomesing.model.ResponsePregunta
import kotlinx.android.synthetic.main.activity_pregunta.*

class PreguntaActivity : AppCompatActivity() {
    lateinit var viewModel: PreguntaViewModel
    var idOfe: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta)
        initApp()


        btn_registrar_pregunta.setOnClickListener {
            createSolicitude()
        }
    }

    private fun initApp() {
        viewModel = ViewModelProviders.of(
            this,
            PreguntaViewModelFactory(
                Injection.getPregunta(),
                ShPreference(
                    getSharedPreferences(
                        PREFERENCE_NAME,
                        Context.MODE_PRIVATE
                    ), this
                )
            )
        ).get(PreguntaViewModel::class.java)
        viewModel.getListPreguntas()

        viewModel.responsePreguntas.observe(this, question)
        viewModel.responseBody.observe(this, responseBody)
    }

    private val responseBody = Observer<ResponsePregunta> {
        Toast.makeText(this, it.cMsj + " " + it.cMsjDetail, Toast.LENGTH_LONG).show()
        if (it.cMsj == "OK") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        else {
            cleanFields()
        }
    }

    private fun cleanFields() {
        et_correo.setText("")
        et_numero_contacto.setText("")
    }

    private val question = Observer<List<Pregunta>> {
        val getQuestion1 = it.filter { key -> key.idGruPreg == 1 }
        val getQuestion2 = it.filter { key -> key.idGruPreg == 2 }
        val getQuestion3 = it.filter { key -> key.idGruPreg == 3 }
        spinnerAdapterQuestion1(getQuestion1)
        spinnerAdapterQuestion2(getQuestion2)
        spinnerAdapterQuestion3(getQuestion3)
    }


    private fun spinnerAdapterQuestion1(listQuestion: List<Pregunta>) {
        val arrayAdapter: ArrayAdapter<Pregunta> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listQuestion)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion2.adapter = arrayAdapter
    }

    private fun spinnerAdapterQuestion2(listQuestion: List<Pregunta>) {
        val arrayAdapter: ArrayAdapter<Pregunta> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listQuestion)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion3.adapter = arrayAdapter
    }

    private fun spinnerAdapterQuestion3(listQuestion: List<Pregunta>) {
        val arrayAdapter: ArrayAdapter<Pregunta> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listQuestion)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        pregunta_opcion4.adapter = arrayAdapter
    }

    private fun emptyFields(): Boolean {
        var validate = true
        if (et_numero_contacto.text.toString().isNullOrEmpty()) {
            et_numero_contacto.error = "Requerido"
            validate = false
        } else {
            et_numero_contacto.error = null
        }
        if (et_correo.text.toString().isNullOrEmpty()) {
            et_correo.error = "Requerido"
            validate = false
        } else {
            et_correo.error = null
        }
        return validate
    }

    private fun createSolicitude() {
        if (!emptyFields()) {
            return
        }
        val option2 = pregunta_opcion2.selectedItem as Pregunta
        val option3 = pregunta_opcion3.selectedItem as Pregunta
        val option4 = pregunta_opcion4.selectedItem as Pregunta

        val data = intent.getIntExtra("idOfer", idOfe)
        viewModel.createSolicitude(
            option2.idPreg,
            option3.idPreg,
            option4.idPreg,
            et_numero_contacto.text.toString(),
            et_correo.text.toString(),
            data
        )
    }

}
