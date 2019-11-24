package com.example.demomesing.features.home.ui.add

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.di.Injection
import com.example.demomesing.model.ResponseUsuario
import kotlinx.android.synthetic.main.fragment_tools.*

class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addViewModel =
            ViewModelProviders.of(this, AddViewModelFactory(Injection.getHome()))
                .get(AddViewModel::class.java)



        return inflater.inflate(R.layout.fragment_tools, container, false)
    }

    private val message = Observer<ResponseUsuario> {
        loader()
        Toast.makeText(context, it.cMsj + "\n" + it.cMsjDetail, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initApp()
        btn_registrar.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        showProgressBar()
        if (!emptyFields()) {
            loader()
            return
        }
        addViewModel.createUser(
            et_nombre_usu_registro.text.toString(),
            et_ape_pat_usu_registro.text.toString(),
            et_ape_mat_usu_registro.text.toString(),
            et_email_usu_registro.text.toString(),
            et_usurio_registro.text.toString(),
            et_pwd_registro.text.toString()
        )
    }

    private fun cleanFields() {
        et_nombre_usu_registro.setText("")
        et_ape_pat_usu_registro.setText("")
        et_ape_mat_usu_registro.setText("")
        et_email_usu_registro.setText("")
        et_usurio_registro.setText("")
        et_pwd_registro.setText("")
    }

    private fun initApp() {
        addViewModel.msj.observe(this, message)
    }

    private fun emptyFields(): Boolean {
        var validate = true
        if (et_nombre_usu_registro.text.isNullOrEmpty()) {
            et_nombre_usu_registro.error = "Requerido"
            validate = false
            toast("Ingrese nombre")
        } else {
            et_nombre_usu_registro.error = null
        }
        if (et_ape_pat_usu_registro.text.isNullOrEmpty()) {
            et_ape_pat_usu_registro.error = "Requerido"
            validate = false
            toast("Ingrese apellido paterno")
        } else {
            et_ape_pat_usu_registro.error = null
        }
        if (et_ape_mat_usu_registro.text.isNullOrEmpty()) {
            et_ape_mat_usu_registro.error = "Requerido"
            validate = false
            toast("Ingrese apellido materno")
        } else {
            et_ape_mat_usu_registro.error = null
        }
        if (et_email_usu_registro.text.isNullOrEmpty()) {
            et_email_usu_registro.error = "Requerido"
            validate = false
            toast("Ingrese email")
        } else {
            et_email_usu_registro.error = null
        }
        if (et_usurio_registro.text.isNullOrEmpty()) {
            et_usurio_registro.error = "Requerido"
            validate = false
            toast("Ingrese nombre de usuario")
        } else {
            et_usurio_registro.error = null
        }
        if (et_pwd_registro.text.isNullOrEmpty()) {
            et_pwd_registro.error = "Requerido"
            validate = false
            toast("Ingrese contraseña")
        } else {
            et_pwd_registro.error = null
        }
        return validate
    }

    private fun loader() {
        Handler().postDelayed({ hideProgressBarr() }, 500)
    }

    private fun hideProgressBarr() {
        progressBarRegister.visibility = View.GONE
    }

    private fun showProgressBar() {
        activity!!.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        )
        progressBarRegister.visibility = View.VISIBLE
    }

    private fun toast(cad: String) {
        Toast.makeText(context, cad, Toast.LENGTH_LONG).show()
    }
}