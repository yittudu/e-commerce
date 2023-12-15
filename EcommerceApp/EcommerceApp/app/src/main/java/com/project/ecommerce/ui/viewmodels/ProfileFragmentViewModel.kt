package com.project.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.project.ecommerce.data.entity.UserManager
import com.project.ecommerce.data.entity.UserResponse
import com.project.ecommerce.data.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(

    private val authRepository: AuthRepository,
) :
    ViewModel() {
    val getUserResult = MutableLiveData<UserResponse>()
    val exception = MutableLiveData<String>()
    fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val user = UserManager.getUser()
                if (user != null) {
                    val result = authRepository.getUserById(user.id)
                    getUserResult.value = result
                }
            } catch (e: HttpException) {
                exception.value = e.response()?.errorBody()?.string()
            } catch (e: Exception) {
                exception.value = e.message.toString()
                Log.e("HATA", e.message.toString())
            }


        }
    }

    fun updateUser(
        userId: Int,
        map: HashMap<String, Any>, onComplete: (result: UserResponse) -> Unit,
    ) {
        CoroutineScope(Dispatchers.Main).launch {

            onComplete(authRepository.updateUser(userId, map))
        }
    }

}