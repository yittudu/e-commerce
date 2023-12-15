package com.project.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ecommerce.data.entity.Product
import com.project.ecommerce.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private var authRepository: AuthRepository) :ViewModel() {
    val productList = MutableLiveData<List<Product>>()
    val categoryList=MutableLiveData<List<String>>()

    init {
        getProducts()
        getAllCategories()
    }

    fun getProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                productList.value = authRepository.getProducts().products
            }catch (e: Exception){
                Log.e("HATA",e.message.toString())
            }

        }
    }

    fun getAllCategories() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val categories = authRepository.getAllCategories()
                val newList = mutableListOf("All").apply {
                    addAll(categories)
                }
                categoryList.value =newList
            }catch (e: Exception){
                Log.e("HATA",e.message.toString())
            }

        }
    }

    fun getCategoryProduct(categoryName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                productList.value = authRepository.getCategoryProduct(categoryName).products
            }catch (e: Exception){
                Log.e("HATA",e.message.toString())
            }

        }
    }



     fun searchProducts(query:String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                productList.value = authRepository.searchProducts(query).products
            }catch (e: Exception){
                Log.e("HATA",e.message.toString())
            }

        }
    }

}