package com.project.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.ecommerce.data.entity.AuthResponse
import com.project.ecommerce.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private var authRepository: AuthRepository) :
    ViewModel() {

    val user = MutableLiveData<AuthResponse?>()
    val exception = MutableLiveData<String?>()

    fun login(username: String, password: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                user.value= authRepository.login(username,password)
            } catch (e: HttpException) {
                exception.value = e.response()?.errorBody()?.string()
            }
            catch (e: Exception){
                exception.value=e.message.toString()
                Log.e("HATA",e.message.toString())
            }
        }
    }
}