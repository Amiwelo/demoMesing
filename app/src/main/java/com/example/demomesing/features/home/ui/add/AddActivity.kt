package com.example.demomesing.features.home.ui.add

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.base.BaseActivity
import com.example.demomesing.di.Injection
import com.example.demomesing.model.ResponseUsuario
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.fragment_tools.*

class AddActivity : BaseActivity() {
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initApp()

        btn_registrar_activity.setOnClickListener { createUser() }
    }

    override fun getLayout(): Int {
        return R.layout.activity_add
    }

    private val message = Observer<ResponseUsuario> {
        loader()
        toast( it.cMsj + "\n" + it.cMsjDetail)
    }
    private fun createUser() {
        showProgressBar()
        if (!emptyFields()) {
            loader()
            return
        }
        addViewModel.createUser(
            et_nombre_usu_registro_activity.text.toString(),
            et_ape_pat_usu_registro_activity.text.toString(),
            et_ape_mat_usu_registro_activity.text.toString(),
            et_email_usu_registro_activity.text.toString(),
            et_usurio_registro_activity.text.toString(),
            et_pwd_registro_activity.text.toString()
        )
    }

    private fun cleanFields() {
        et_nombre_usu_registro_activity.setText("")
        et_ape_pat_usu_registro_activity.setText("")
        et_ape_mat_usu_registro_activity.setText("")
        et_email_usu_registro_activity.setText("")
        et_usurio_registro_activity.setText("")
        et_pwd_registro_activity.setText("")
    }

    private fun initApp() {
        progressBar = progressBarRegisterActivity
        addViewModel =
            ViewModelProviders.of(this, AddViewModelFactory(Injection.getHome()))
                .get(AddViewModel::class.java)
        addViewModel.msj.observe(this, message)
    }

    private fun emptyFields(): Boolean {
        var validate = true
        if (et_nombre_usu_registro_activity.text.isNullOrEmpty()) {
            et_nombre_usu_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese nombre")
        } else {
            et_nombre_usu_registro_activity.error = null
        }
        if (et_ape_pat_usu_registro_activity.text.isNullOrEmpty()) {
            et_ape_pat_usu_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese apellido paterno")
        } else {
            et_ape_pat_usu_registro.error = null
        }
        if (et_ape_mat_usu_registro_activity.text.isNullOrEmpty()) {
            et_ape_mat_usu_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese apellido materno")
        } else {
            et_ape_mat_usu_registro_activity.error = null
        }
        if (et_email_usu_registro_activity.text.isNullOrEmpty()) {
            et_email_usu_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese email")
        } else {
            et_email_usu_registro_activity.error = null
        }
        if (et_usurio_registro_activity.text.isNullOrEmpty()) {
            et_usurio_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese nombre de usuario")
        } else {
            et_usurio_registro_activity.error = null
        }
        if (et_pwd_registro_activity.text.isNullOrEmpty()) {
            et_pwd_registro_activity.error = "Requerido"
            validate = false
            toast("Ingrese contraseña")
        } else {
            et_pwd_registro_activity.error = null
        }
        return validate
    }

    private fun loader() {
        Handler().postDelayed({ hideProgressBarr() }, 500)
    }


}
