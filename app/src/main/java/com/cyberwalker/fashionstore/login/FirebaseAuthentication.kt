package com.cyberwalker.fashionstore.login

import com.cyberwalker.fashionstore.util.AuthenticationResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

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

    /* Question: commented until I know how to get the context
    fun loginWithProvider(
        firebaseAuth: FirebaseAuth,
        providerId: String,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (Exception?) -> Unit
    ) {
        val gitHubProvider = OAuthProvider.newBuilder(providerId)
        val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener { result ->
                    onSuccess(result)
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        } else {
            firebaseAuth.startActivityForSignInWithProvider(context, gitHubProvider.build())
                .addOnSuccessListener { result ->
                    onSuccess(result)
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        }
    } */
}