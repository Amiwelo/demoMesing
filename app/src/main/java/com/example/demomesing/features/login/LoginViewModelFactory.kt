package com.example.demomesing.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demomesing.data.session.ShPreference

class LoginViewModelFactory (private val dataSource: LoginDataSource, private val shPreference: ShPreference ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(dataSource, shPreference) as T
    }
}