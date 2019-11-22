package com.example.demomesing.di

import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.features.home.MainRepository
import com.example.demomesing.features.login.LoginDataSource
import com.example.demomesing.features.login.LoginRepository

object Injection {
    fun getLogin():LoginDataSource{
        return LoginRepository()
    }
    fun getHome():MainDataSource{
        return MainRepository()
    }
}