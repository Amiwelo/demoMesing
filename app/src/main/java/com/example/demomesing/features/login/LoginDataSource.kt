package com.example.demomesing.features.login

import com.google.firebase.auth.FirebaseAuth

interface LoginDataSource {
    fun signIn(email: String, pwd: String, auth: FirebaseAuth)
}