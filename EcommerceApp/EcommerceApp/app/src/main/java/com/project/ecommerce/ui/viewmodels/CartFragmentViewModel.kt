package com.project.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ecommerce.data.entity.UserManager
import com.project.ecommerce.data.entity.usercart.UserCartResponse
import com.project.ecommerce.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    private var authRepository: AuthRepository
) :
    ViewModel() {
    val cartList = MutableLiveData<UserCartResponse>()

    init {
        getCartList()
    }


    fun getCartList() {
        CoroutineScope(Dispatchers.Main).launch {
            val user = UserManager.getUser() //we can use this user.id but its cart is empty!!.
            val userCartResponse
                    = authRepository.getUserCart(5)
            Log.e("TAG",userCartResponse.total.toString())

            cartList.value = userCartResponse
        }
    }

}