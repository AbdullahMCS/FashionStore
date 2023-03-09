package com.cyberwalker.fashionstore.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cyberwalker.fashionstore.util.AuthenticationResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


// Question: what is savedStateHandle?
@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    // Question: how to initialize firebase with context in viewmodel
    // private val firebaseAuth = FirebaseAuth.getInstance()

    private val _userLoginStatus =
        MutableStateFlow<AuthenticationResult<AuthResult>>(AuthenticationResult.Loading())
    val userLoginStatus get() = _userLoginStatus

    fun login(email: String, password: String) {
        _userLoginStatus.value = AuthenticationResult.Loading()
        FirebaseAuthentication.login(
            firebaseAuth,
            email,
            password,
            onSuccess = {
                _userLoginStatus.value = AuthenticationResult.Success(it)
                Log.d("LoginScreen", "viewmodel success: ${firebaseAuth.currentUser?.email}")
                Log.d("LoginScreen", "viewmodel Success: ${userLoginStatus.value is AuthenticationResult.Success}")
                Log.d("LoginScreen", "viewmodel Error: ${userLoginStatus.value is AuthenticationResult.Error}")
                Log.d("LoginScreen", "viewmodel Loading: ${userLoginStatus.value is AuthenticationResult.Loading}")
            },
            onFailure = {
                _userLoginStatus.value = AuthenticationResult.Error(it?.message)
                Log.d("LoginScreen", "viewmodel error: ${firebaseAuth.currentUser?.email}")
            }
        )
    }

    //Question: commented until I know how to get the context
//    fun loginWithGitHub() {
//        FirebaseAuthentication.loginWithProvider(
//            firebaseAuth,
//            "github.com",
//            onSuccess = {
//                _userLoginStatus.value = AuthenticationResult.Success(it)
//                Log.d("LoginScreen", "viewmodel Success: ${userLoginStatus.value is AuthenticationResult.Success}")
//                Log.d("LoginScreen", "viewmodel Error: ${userLoginStatus.value is AuthenticationResult.Error}")
//                Log.d("LoginScreen", "viewmodel Loading: ${userLoginStatus.value is AuthenticationResult.Loading}")
//            },
//            onFailure = {
//                _userLoginStatus.value = AuthenticationResult.Error(it?.message)
//                Log.d("LoginScreen", "viewmodel error: ${firebaseAuth.currentUser?.email}")
//            }
//        )
//    }
}