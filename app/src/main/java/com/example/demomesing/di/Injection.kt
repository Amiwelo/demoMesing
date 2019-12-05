package com.example.demomesing.di

import com.example.demomesing.features.home.MainDataSource
import com.example.demomesing.features.home.MainRepository
import com.example.demomesing.features.login.LoginDataSource
import com.example.demomesing.features.login.LoginRepository
import com.example.demomesing.features.solicitud.PreguntaDataSource
import com.example.demomesing.features.solicitud.PreguntaRepository

object Injection {
    fun getLogin():LoginDataSource{
        return LoginRepository()
    }
    fun getHome():MainDataSource{
        return MainRepository()
    }

    fun getPregunta(): PreguntaDataSource {
        return PreguntaRepository()
    }

    fun getSolicitude(): MainDataSource{
        return MainRepository()
    }
}