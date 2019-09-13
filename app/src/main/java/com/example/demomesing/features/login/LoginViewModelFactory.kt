package com.example.demomesing.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class LoginViewModelFactory (private val dataSource: LoginDataSource,private val auth: FirebaseAuth): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(dataSource, auth) as T
    }
}