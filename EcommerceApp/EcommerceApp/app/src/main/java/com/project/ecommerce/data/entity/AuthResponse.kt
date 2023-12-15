package com.project.ecommerce.data.entity

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String,
)