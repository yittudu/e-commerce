package com.project.ecommerce.data.entity.cart

data class AddToCartRequest(
    val userId: Int,
    val products: List<ProductInCart>
)
