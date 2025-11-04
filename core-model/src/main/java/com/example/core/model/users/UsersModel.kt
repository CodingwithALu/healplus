package com.example.core.model.users
data class UserModel(
    var idUser: String ,
    var name: String,
    var email: String,
    var password: String,
    var gender: String,
    var dateBirth: String,
    var url: String,
    var spot: Int = 0,
    var token: String,
    val role: String?
){
    companion object {
        fun empty() = UserModel(
            idUser = "",
            name = "",
            email = "",
            password = "",
            gender = "",
            dateBirth = "",
            url = "",
            spot = 0,
            token = "",
            role = ""
        )
    }
    fun toJsonMap(): Map<String, Any?>{
        return mapOf(
            "id" to idUser,
            "name" to name,
            "email" to email,
            "password" to password,
            "gender" to gender,
            "dateBirth" to dateBirth,
            "url" to url,
            "spot" to spot,
            "token" to token,
            "role" to role
        )
    }
}
