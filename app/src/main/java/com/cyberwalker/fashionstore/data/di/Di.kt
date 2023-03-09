/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyberwalker.fashionstore.data.di

import android.app.Application
import android.content.Context
import com.cyberwalker.fashionstore.data.HomeRepository
import com.cyberwalker.fashionstore.data.source.HomeRemoteSource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object Di {

    fun getHomeRemoteSource() = HomeRemoteSource()

    fun getHomeRepository(source: HomeRemoteSource) = HomeRepository(source)

    @Provides
    fun provideFirebaseAuth(
        @ApplicationContext appContext: Context
    ): FirebaseAuth {
        FirebaseApp.initializeApp(appContext)
        return FirebaseAuth.getInstance()
    }

//    @Provides
//    fun provideGSO(): GoogleSignInOptions {
//        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("325406364684-vudtgr2hqhug0qnmju2nc8tn1h3vtqp5.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//    }
//
//    @Provides
//    fun providesGSC(
//        gso: GoogleSignInOptions,
//        @ApplicationContext appContext: Context
//    ): GoogleSignInClient {
//        return GoogleSignIn.getClient(appContext, gso)
//    }

    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named("signInRequest")
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("325406364684-vudtgr2hqhug0qnmju2nc8tn1h3vtqp5.apps.googleusercontent.com")
                .setFilterByAuthorizedAccounts(true)
                .build())
        .setAutoSelectEnabled(true)
        .build()
}