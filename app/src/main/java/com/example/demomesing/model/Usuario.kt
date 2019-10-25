package com.example.demomesing.model

import java.io.Serializable

data class Usuario constructor(
    var nombre: String, var user: String, var pwd: String
):Serializable
