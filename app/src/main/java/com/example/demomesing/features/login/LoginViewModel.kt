package com.example.demomesing.features.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val dataSource: LoginDataSource,private val auth: FirebaseAuth): ViewModel() {

    fun signIn(email: String, pwd: String): Int{
        var estate= -1
        dataSource.signIn(email, pwd, auth)
        if (auth.currentUser!==null){
            estate = 0
        }
        return estate
    }

    fun signInWithGoogle(account: GoogleSignInAccount?): Int {
        var estate=-1
        dataSource.signInWithGoogle(account, auth)
        if(auth.currentUser!=null){
            estate = 0
        }
        return estate
    }

}