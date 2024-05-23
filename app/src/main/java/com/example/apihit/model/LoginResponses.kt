package com.example.apihit.model

data class LoginResponses(
    var username: String?,
    var password: String?,
    var message: String?,
    var status: String?,
    var auth: Any?,
    var expiration_data: Any?
) {}
