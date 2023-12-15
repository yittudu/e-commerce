package com.project.ecommerce.data.entity.usercart


import com.google.gson.annotations.SerializedName

data class UserCartResponse(
    @SerializedName("carts") val carts: List<UserCart>,
    @SerializedName("total") val total: Int,
    @SerializedName("skip") val skip: Int,
    @SerializedName("limit") val limit: Int
)