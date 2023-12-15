package com.project.ecommerce.retrofit

class ApiUtils {
    companion object{
        private const val baseAuthUrl="https://dummyjson.com/"

        fun getAuthDao(): Dao {
            return RetrofitClient.getClient(baseAuthUrl).create(Dao::class.java)
        }

    }
}