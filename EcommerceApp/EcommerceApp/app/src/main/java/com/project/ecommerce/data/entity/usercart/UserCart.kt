package com.project.ecommerce.data.entity.usercart


import com.google.gson.annotations.SerializedName

data class UserCart(
    @SerializedName("id") val id: Int,
    @SerializedName("products") val products: List<UserProduct>,
    @SerializedName("total") val total: Int,
    @SerializedName("discountedTotal") val discountedTotal: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("totalProducts") val totalProducts: Int,
    @SerializedName("totalQuantity") val totalQuantity: Int
)