package com.project.ecommerce.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.project.ecommerce.data.entity.cart.AddToCartRequest
import com.project.ecommerce.data.entity.cart.CartResponse
import com.project.ecommerce.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(

    private var authRepository: AuthRepository
) :
    ViewModel() {

    fun addCart(
        addToCartRequest:AddToCartRequest,
        onSuccess:(response: CartResponse)->Unit,
        onFailure:(exception:String)->Unit
        ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response= authRepository.addCart(addToCartRequest)
                onSuccess(response)
            }catch (e:Exception){
                e.message?.let { onFailure(it) }
            }

        }
    }

}