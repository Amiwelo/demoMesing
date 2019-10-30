package com.example.demomesing.di

import com.example.demomesing.features.home.HomeDataSource
import com.example.demomesing.features.home.HomeRepository
import com.example.demomesing.features.login.LoginDataSource
import com.example.demomesing.features.login.LoginRepository

object Injection {
    fun getLogin():LoginDataSource{
        return LoginRepository()
    }
    fun getHome():HomeDataSource{
        return HomeRepository()
    }
}