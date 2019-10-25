package com.example.demomesing.model

import java.io.Serializable

data class User constructor(
    var status: String,
    var codeStatus: Int,
    var message: String,
    var token: String,
    var tokenExpire: Int,
    var id: String,
    var nameUser: String,
    var lastNameUser: String,
    var email: String,
    var avatar: String,
    var idStatusUser: Int,
    var idRol: Int,
    var idProfile: Int
): Serializable