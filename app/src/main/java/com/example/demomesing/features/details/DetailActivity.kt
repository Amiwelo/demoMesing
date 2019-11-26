package com.example.demomesing.features.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demomesing.R
import com.example.demomesing.features.solicitud.PreguntaActivity
import com.example.demomesing.model.Servicios
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getData()

        btn_solicitar_servicio.setOnClickListener {
            val intent = Intent(this@DetailActivity, PreguntaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData(){
        val data = intent.getSerializableExtra("dataOfertante") as Servicios
        et_celular.text = data.cel_usu
        et_descripcion_serv.text = data.des_tip_serv
        et_nombre_usu.text = data.pri_nom_usu+" "+data.ape_pat_usu+" "+data.ape_mat_usu
        et_ubicacion.text = data.ubicacion
        et_profesion.text = data.seu_usu
        Picasso.get().load(data.ima_usu).placeholder(R.color.blue_bg_light).into(img_profile)
    }
}
