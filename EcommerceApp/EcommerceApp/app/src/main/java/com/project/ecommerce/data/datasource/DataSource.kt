package com.project.ecommerce.data.datasource

import android.util.Log
import com.project.ecommerce.data.entity.AuthResponse
import com.project.ecommerce.data.entity.ProductResponse
import com.project.ecommerce.data.entity.UserResponse
import com.project.ecommerce.data.entity.cart.AddToCartRequest
import com.project.ecommerce.data.entity.cart.CartResponse
import com.project.ecommerce.data.entity.usercart.UserCartResponse

import com.project.ecommerce.retrofit.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSource(var dao: Dao) {
    suspend fun login(username:String,password:String): AuthResponse = withContext(
        Dispatchers.IO) {
        val response=dao.login(username,password)
        Log.e("Login", "Başarı: ${response.firstName}, message: ${response.token} ")
        return@withContext response
    }

    suspend fun getUserById(userId: Int): UserResponse = withContext(
        Dispatchers.IO) {
        val response=dao.getUserById(userId)
        Log.e("getUserById", "Başarı: ${response.firstName}, message: ${response.email} ")
        return@withContext response
    }

    suspend fun updateUser(userId: Int,request: HashMap<String, Any>): UserResponse = withContext(
        Dispatchers.IO) {
        val response=dao.updateUser(userId,request)
        Log.e("updateUser", "Başarı: ${response.firstName}, message: ${response.email} ")
        return@withContext response
    }

    suspend fun getProducts(): ProductResponse = withContext(
        Dispatchers.IO) {
        val response=dao.getProducts()
        Log.e("Get Products", "Başarı: ${response.total}, message: ${response.products.first()} ")
        return@withContext response
    }
    suspend fun searchProducts(query:String): ProductResponse = withContext(
        Dispatchers.IO) {
        val response=dao.searchProducts(query)
        Log.e("searchProducts", "Başarı: ${response.total}, message: ${response.products.first()} ")
        return@withContext response
    }

    suspend fun getAllCategories(): List<String> = withContext(
        Dispatchers.IO) {
        val response=dao.getAllCategories()
        Log.e("getAllCategories", "Başarı: ${response.count()}, message: ${response.first()} ")
        return@withContext response
    }
    suspend fun getCategoryProduct(categoryName: String): ProductResponse = withContext(
        Dispatchers.IO) {
        val response=dao.getCategoryProduct(categoryName)
        Log.e("getCategoryProduct", "Başarı: ${response.total}, message: ${response.products.first()} ")
        return@withContext response
    }

    suspend fun addCart(addToCartRequest: AddToCartRequest): CartResponse = withContext(
        Dispatchers.IO) {
        val response=dao.addToCart(addToCartRequest)
        Log.e("addCart", "Başarı: ${response.total}, message: ${response.products.first()} ")
        return@withContext response
    }

    suspend fun getUserCart(userId: Int): UserCartResponse = withContext(
        Dispatchers.IO) {
        val response=dao.getUserCart(userId)
        Log.e("getUserCart", "Başarı: ${response.total}, message: ${response.carts} ")
        return@withContext response
    }
}