package com.example.demomesing.features.login

import com.example.demomesing.data.ObjectOperation


interface LoginDataSource {
    fun signIn(
        email: String,
        pwd: String,
        param: ObjectOperation
    )
}