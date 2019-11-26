package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pregunta3(
    @SerializedName("ID_PREG")
    val idPreg: Int,
    @SerializedName("DES_PREG")
    val desPreg: String,
    @SerializedName("ID_EST_PREG")
    val idEstPreg: Int,
    @SerializedName("ID_GRU_PREG")
    val idGruPreg: Int
): Serializable {
    override fun toString(): String {
        return desPreg
    }
}
