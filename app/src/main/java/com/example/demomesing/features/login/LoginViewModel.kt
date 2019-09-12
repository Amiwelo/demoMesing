package com.example.demomesing.features.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val dataSource: LoginDataSource): ViewModel() {

    fun signIn(email: String, pwd: String, auth: FirebaseAuth){
        dataSource.signIn(email, pwd, auth)
    }
}