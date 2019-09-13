package com.example.demomesing.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demomesing.R
import com.example.demomesing.model.Usuario
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //init()
    }
    private fun init(){
        val user: Usuario = intent.extras?.getSerializable("objUsuario") as Usuario
        tv_usuario.text = user.user
    }
}
