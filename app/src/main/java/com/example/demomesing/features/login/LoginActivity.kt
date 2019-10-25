package com.example.demomesing.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demomesing.R
import com.example.demomesing.base.BaseActivity
import com.example.demomesing.di.Injection
import com.example.demomesing.features.home.HomeActivity
import com.example.demomesing.model.User
import kotlinx.android.synthetic.main.activity_main.*

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
        viewModel.responseBody.observe(this, response)
    }
    private val response = Observer<User>{
        if(it.message=="OK"){
            val user = viewModel.responseBody.value!!
            sendHome(user)
        } else {
            toast("Error usuario fallo")
        }
    }

    private fun sendHome(user: User) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("objUser", user)
        startActivity(intent)
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
