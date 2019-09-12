package com.example.demomesing.di

import com.example.demomesing.features.login.LoginDataSource
import com.example.demomesing.features.login.LoginRepository

object Injection {
    fun getLogin():LoginDataSource{
        return LoginRepository()
    }
}