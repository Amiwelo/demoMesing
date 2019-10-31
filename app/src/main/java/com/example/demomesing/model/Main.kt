package com.example.demomesing.model

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("ID_GRU")
    var idGroup: Int,
    @SerializedName("NOM_GRU")
    var namGroup: String,
    @SerializedName("ICO_GRU")
    var icoGroup: String
)