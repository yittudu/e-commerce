package com.project.ecommerce.data.entity

object UserManager {
    private var user: AuthResponse? = null

    fun setUser(newUser: AuthResponse) {
        user = newUser
    }

    fun getUser(): AuthResponse? {
        return user
    }
}