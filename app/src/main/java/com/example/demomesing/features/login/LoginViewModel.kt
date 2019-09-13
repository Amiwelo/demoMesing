package com.example.demomesing.features.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val dataSource: LoginDataSource,private val auth: FirebaseAuth): ViewModel() {

    fun signIn(email: String, pwd: String): Boolean{
        var estado= false
        dataSource.signIn(email, pwd, auth)
        if (auth.currentUser?.isEmailVerified==true){
            estado = true
        }
        return estado
    }

    fun signInWithGoogle(account: GoogleSignInAccount?): Int {
        var estado=-1
        dataSource.signInWithGoogle(account, auth)
        if(auth.currentUser==null){
            estado = 0
        }
        return estado
    }

}