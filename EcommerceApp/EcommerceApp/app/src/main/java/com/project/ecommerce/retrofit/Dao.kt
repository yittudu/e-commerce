package com.project.ecommerce.retrofit
import com.project.ecommerce.data.entity.AuthResponse
import com.project.ecommerce.data.entity.ProductResponse
import com.project.ecommerce.data.entity.UserResponse
import com.project.ecommerce.data.entity.cart.AddToCartRequest
import com.project.ecommerce.data.entity.cart.CartResponse
import com.project.ecommerce.data.entity.usercart.UserCartResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Dao {
    @Headers("Content-Type': 'application/json")
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): AuthResponse

    @Headers("Content-Type': 'application/json")
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): UserResponse

    @Headers("Content-Type: application/json")
    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") userId: Int,
        @Body request: HashMap<String, Any>
    ): UserResponse

    @Headers("Content-Type': 'application/json")
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @Headers("Content-Type: application/json")
    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): ProductResponse

    @Headers("Content-Type: application/json")
    @GET("products/categories")
    suspend fun getAllCategories(
    ): List<String>

    @Headers("Content-Type: application/json")
    @GET("products/category/{categoryName}")
    suspend fun getCategoryProduct(
        @Path("categoryName") categoryName: String,
    ): ProductResponse

    @Headers("Content-Type: application/json")
    @POST("carts/add")
    suspend fun addToCart(
        @Body addToCartRequest: AddToCartRequest
    ): CartResponse

    @Headers("Content-Type: application/json")
    @GET("carts/user/{userId}")
    suspend fun getUserCart(@Path("userId") userId: Int): UserCartResponse




}