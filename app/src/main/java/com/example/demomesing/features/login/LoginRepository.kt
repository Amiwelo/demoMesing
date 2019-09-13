package com.example.demomesing.features.login

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.internal.Preconditions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class LoginRepository: LoginDataSource {
    override fun signIn(email: String, pwd: String, auth: FirebaseAuth) {
        var auth = FirebaseAuth.getInstance()
        Preconditions.checkNotEmpty(email, "ingrese email")
        Preconditions.checkNotEmpty(pwd, "ingrese password")
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
            if(it.isSuccessful) run {
                val user = auth.currentUser
                Log.i("Ingreso Sign", ""+user)
                if(it.isComplete){

                }
            }
            else{
                Log.w("Error Sign:", "failure")
            }
        }

    }

    override fun signInWithGoogle(account: GoogleSignInAccount?, auth: FirebaseAuth) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        try {
            auth.signInWithCredential(credential).addOnCompleteListener {
                if(it.isSuccessful){
                    val user = auth.currentUser
                    Log.i("Ingreso Sign", ""+user)
                }else{
                    Log.w("Error Sign:", "failure")
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}