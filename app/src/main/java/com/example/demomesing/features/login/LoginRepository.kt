package com.example.demomesing.features.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
class LoginRepository: LoginDataSource {
    override fun signIn(email: String, pwd: String, auth: FirebaseAuth) {
        var auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
            if(it.isSuccessful) run {
                val user = auth.currentUser
                Log.i("Ingreso Sign", ""+user)
            }
            else{
                Log.w("Error Sign:", "failure")
            }
        }

    }
}