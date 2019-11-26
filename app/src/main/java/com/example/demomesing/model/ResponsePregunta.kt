package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponsePregunta(
    @SerializedName("cMsj")
    val cMsj: String,
    @SerializedName("cMsjDetaill")
    val cMsjDetail: String,
    @SerializedName("DtCollection")
    val listPreguntas: List<Pregunta>
) : Serializable