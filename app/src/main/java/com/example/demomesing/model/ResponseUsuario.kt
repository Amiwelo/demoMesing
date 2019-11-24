package com.example.demomesing.model

import com.google.gson.annotations.SerializedName

data class ResponseUsuario (
    @SerializedName("cMsj")
    val cMsj: String,
    @SerializedName("cMsjDetaill")
    val cMsjDetail: String,
    @SerializedName("DtCollection")
    val listObjects: List<Usuario>,
    @SerializedName("nMsjCode")
    val codeStatus: Int
)