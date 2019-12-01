package com.example.demomesing.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResponseData (
@SerializedName("Status")
var status: String,
@SerializedName("CodeStatus")
var codeStatus: Int,
@SerializedName("Message")
var message: String,
@SerializedName("DtCollection")
var objJson: List<JsonObject>
)