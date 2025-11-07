package com.example.core.model.users
data class UserModel(
    var id: String,
    var name: String,
    var email: String,
    var password: String,
    var gender: String? = null,
    var dateBirth: String? = null,
    var url: String? = null,
    var spot: Int = 0,
    var token: String? = null,
    val role: String = ""
){
    companion object {
        fun empty() = UserModel(
            id = "",
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
            "id" to id,
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
