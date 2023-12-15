package com.project.ecommerce.data.entity

data class User(
    var userId: String,
    var userName: String,
    var userSurname: String,
    var userPhoneNumber: String,
    var userAddress: String,
){
    companion object {
        fun fromMap(map: Map<String, Any>): User {
            return User(
                userId = map["userId"] as String,
                userName = map["userName"] as String,
                userSurname = map["userSurname"] as String,
                userPhoneNumber = map["userPhoneNumber"] as String,
                userAddress = map["userAddress"] as String
            )
        }
    }

    fun toMap(): Map<String, Any> {
        return mapOf(
            "userId" to userId,
            "userName" to userName,
            "userSurname" to userSurname,
            "userPhoneNumber" to userPhoneNumber,
            "userAddress" to userAddress,

        )
    }
}
