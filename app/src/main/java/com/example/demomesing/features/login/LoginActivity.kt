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
import com.example.demomesing.model.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class LoginActivity : BaseActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "informacion"
        private const val RC_SIGN_IN = 9001
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_google -> signIn()
            R.id.btn_ingresar -> signInSimple()
        }
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignIn: GoogleSignInClient
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignIn = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()
        init()
        btn_sign_google.setOnClickListener(this)
        btn_ingresar.setOnClickListener(this)
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, LoginViewModelFactory(Injection.getLogin(), auth))
            .get(LoginViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            if (viewModel.signInWithGoogle(account) == 0) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                //intent.putExtra("objUsuario", )
                startActivity(intent)
            } else {
                toast("usuario gmail invalido")
            }
        } else {
            toast("RC_SIGN_IN invalido")
        }
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

    private fun signIn() {
        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(
            signInIntent,
            RC_SIGN_IN
        )
    }

    private fun signInSimple() {
        Log.i(TAG, "signInSimple")
        if(!fieldsEmpty()) return

        if (viewModel.signIn(et_user.text.toString(), et_password.text.toString()) == 0) {
            val objUser = Usuario("", et_user.text.toString(), et_password.text.toString())
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.putExtra("objUsuario", objUser)
            startActivity(intent)
        }else {
            toast("usuario invalido")
        }
    }


}
