package com.project.ecommerce.data.entity.usercart


import com.google.gson.annotations.SerializedName

data class UserProduct(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("discountedPrice") val discountedPrice: Int,
    @SerializedName("thumbnail") val thumbnail: String
)