package com.example.demomesing.data

import com.example.demomesing.model.Collection
import com.example.demomesing.model.ResponseData
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CallServices {
    @POST("authenticate/token")
    @FormUrlEncoded
    suspend fun loggin(@FieldMap param: Map<String, String>): Response<ResponseData>

    @POST("menu/lstMenu")
    @FormUrlEncoded
    suspend fun lstMain(@FieldMap param: Map<String, String>): Response<Collection>
}
