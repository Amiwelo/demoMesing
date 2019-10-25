package com.example.demomesing.data

import com.example.demomesing.model.User
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CallServices {
    @POST("authenticate/token")
    @FormUrlEncoded
    suspend fun loggin(@FieldMap param: Map<String, String>): Response<User>
}
