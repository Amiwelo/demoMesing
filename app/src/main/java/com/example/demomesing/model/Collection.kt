package com.example.demomesing.model

import com.google.gson.annotations.SerializedName

data class Collection (
    @SerializedName("cMsj")
    var msj: String,
    @SerializedName("DtCollection")
    var collection: List<Main>
)