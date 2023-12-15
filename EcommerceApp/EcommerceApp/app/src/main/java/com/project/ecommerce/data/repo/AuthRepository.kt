package com.project.ecommerce.data.repo

import com.project.ecommerce.data.datasource.DataSource
import com.project.ecommerce.data.entity.cart.AddToCartRequest

class AuthRepository(var dataSource: DataSource) {
    suspend fun login(userName: String, password: String) =
        dataSource.login(userName, password)

    suspend fun getUserById(userId: Int) =
        dataSource.getUserById(userId)

    suspend fun updateUser(userId: Int, request: HashMap<String, Any>) =
        dataSource.updateUser(userId, request)

    suspend fun getProducts() =
        dataSource.getProducts()

    suspend fun searchProducts(query: String) =
        dataSource.searchProducts(query)

    suspend fun getAllCategories() =
        dataSource.getAllCategories()

    suspend fun getCategoryProduct(categoryName: String) =
        dataSource.getCategoryProduct(categoryName)

    suspend fun addCart(addToCartRequest:AddToCartRequest) =
        dataSource.addCart(addToCartRequest)

    suspend fun getUserCart(userId: Int) =
        dataSource.getUserCart(userId)


}