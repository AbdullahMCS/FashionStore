package com.cyberwalker.fashionstore.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.cyberwalker.fashionstore.MainActivity
import com.cyberwalker.fashionstore.util.AuthenticationResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

object FirebaseAuthentication {
    fun login(
        firebaseAuth: FirebaseAuth,
        email: String,
        password: String,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (Exception?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(task.result)
                } else {
                    onFailure(task.exception)
                }
            }
    }

    // Question: commented until I know how to get the context
//    fun loginWithProvider(
//        firebaseAuth: FirebaseAuth,
//        providerId: String,
//        onSuccess: (AuthResult) -> Unit,
//        onFailure: (Exception?) -> Unit,
//        activity: Activity
//    ) {
//        val gitHubProvider = OAuthProvider.newBuilder(providerId)
//        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
//        if (pendingResultTask != null) {
//            pendingResultTask
//                .addOnSuccessListener { result ->
//                    onSuccess(result)
//                }
//                .addOnFailureListener {
//                    onFailure(it)
//                }
//        } else {
//            firebaseAuth.startActivityForSignInWithProvider(activity, gitHubProvider.build())
//                .addOnSuccessListener { result ->
//                    onSuccess(result)
//                }
//                .addOnFailureListener {
//                    onFailure(it)
//                }
//        }
//    }
}