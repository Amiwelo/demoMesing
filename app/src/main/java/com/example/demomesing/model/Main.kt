package com.example.demomesing.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Main (
    @SerializedName("ID_MEN")
    var idMain: String,
    @SerializedName("NOM_MEN")
    var nomMain: String,
    @SerializedName("RUT_MEN")
    var rutMain: String,
    @SerializedName("ID_GRU")
    var idGroup: Int/*,
    @SerializedName("NOM_GRU")
    var namGroup: String,
    @SerializedName("ICO_GRU")
    var icoGroup: String*/
):Serializable