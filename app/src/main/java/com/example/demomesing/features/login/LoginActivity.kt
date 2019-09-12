package com.example.demomesing.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_google -> signIn()
            R.id.btn_ingresar -> ingresarSimple()
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
        viewModel = ViewModelProviders.of(this, LoginViewModelFactory(Injection.getLogin()))
            .get(LoginViewModel::class.java)
        btn_sign_google.setOnClickListener(this)
        btn_ingresar.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "Ingreso", Toast.LENGTH_SHORT).show()
            //btn_sign_google.visibility = View.GONE
        } else {
            btn_sign_google.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                authWithGoogle(account)
            } catch (e: Exception) {
                e.printStackTrace()
                updateUI(null)
            }
        }
    }

    private fun authWithGoogle(account: GoogleSignInAccount?) {
        Log.i("account: ", "${account?.id}")

        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.i(TAG, "SUCCESS")
                val user = auth.currentUser
                updateUI(user)
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.exception)
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(
            signInIntent,
            RC_SIGN_IN
        )
    }

    private fun ingresarSimple(){
        viewModel.signIn(et_user.text.toString(), et_password.text.toString(), auth)
        val objuser = Usuario("", et_user.text.toString(), et_password.text.toString())
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
