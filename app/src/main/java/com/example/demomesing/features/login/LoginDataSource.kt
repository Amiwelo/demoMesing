package com.example.demomesing.features.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

interface LoginDataSource {
    fun signIn(email: String, pwd: String, auth: FirebaseAuth)
    fun signInWithGoogle(account: GoogleSignInAccount?, auth: FirebaseAuth)
}