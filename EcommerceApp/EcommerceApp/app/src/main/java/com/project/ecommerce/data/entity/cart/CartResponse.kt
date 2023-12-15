package com.project.ecommerce.data.entity.cart


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("discountedTotal")
    val discountedTotal: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int,
    @SerializedName("userId")
    val userId: Int
)