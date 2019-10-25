package com.example.demomesing.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.base.BaseActivity
import com.example.demomesing.di.Injection
import com.example.demomesing.features.home.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.example.demomesing.model.Usuario as Usuario

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_ingresar -> signInService()
        }
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initApp()
        btn_ingresar.setOnClickListener(this)
    }
    private fun initApp() {
        viewModel = ViewModelProviders.of(
            this,
            LoginViewModelFactory(Injection.getLogin())).get(LoginViewModel::class.java)
    }



    private fun fieldsEmpty(): Boolean {
        var validate = true

        if (et_user.text.isEmpty()) {
            et_user.error = "Requerid."
            validate = false
            toast("ingrese usuario")
        } else {
            et_user.error = null
        }

        if (et_password.text.isEmpty()) {
            et_password.error = "Requerid."
            validate = false
            toast("ingrese contraseña")
        } else {
            et_password.error = null
        }
        return validate
    }



    private fun signInService(){
        if (!fieldsEmpty()) {
            return
        }
        Log.i("Info", "SignInService")
        viewModel.signInService(et_user.text.toString(), et_password.text.toString())
    }


}
