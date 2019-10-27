package com.example.demomesing.data.session

import android.content.Context
import android.content.SharedPreferences
import com.example.demomesing.BuildConfig
import com.example.demomesing.model.User
import com.google.gson.Gson

class ShPreference constructor(private val sharedPreferences: SharedPreferences, private val context: Context) {

    companion object {
        const val PREFERENCE_NAME = BuildConfig.APPLICATION_ID
        private const val TOKEN = "USER"
    }

    var user: User?
        get() = Gson().fromJson(
            context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(
                TOKEN,
                ""
            ), User::class.java
        )
        set(value) {
            sharedPreferences.edit().putString(TOKEN, Gson().toJson(value)).apply()
        }
}