package com.example.demomesing.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResponseService (
    @SerializedName("cMsj")
    val cMsj: String,
    @SerializedName("cMsjDetaill")
    val cMsjDetail: String,
    @SerializedName("DtCollection")
    val listObjects: List<JsonObject>
)