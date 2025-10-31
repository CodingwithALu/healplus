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
}
