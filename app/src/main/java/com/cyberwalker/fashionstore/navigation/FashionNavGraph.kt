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
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.cyberwalker.fashionstore.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.detail.DetailScreen
import com.cyberwalker.fashionstore.detail.DetailScreenActions
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.dump.animatedComposable
import com.cyberwalker.fashionstore.home.HomeScreen
import com.cyberwalker.fashionstore.home.HomeScreenActions
import com.cyberwalker.fashionstore.liked.LikedScreen
import com.cyberwalker.fashionstore.login.LoginScreen
import com.cyberwalker.fashionstore.login.LoginScreenActions
import com.cyberwalker.fashionstore.profile.ProfileScreen
import com.cyberwalker.fashionstore.search.SearchScreen
import com.cyberwalker.fashionstore.splash.SplashScreen
import com.cyberwalker.fashionstore.splash.SplashScreenActions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class Screen(val name: String, val route: String) {
    object Splash : Screen("splash", "splash")
    object Home : Screen("home", "home")
    object Detail : Screen("detail", "detail")
    object Login : Screen("login", "login")
    object Search : Screen("search", "search")
    object Profile : Screen("profile", "profile")
    object Liked : Screen("liked", "liked")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FashionNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    actions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = modifier
        ) {
            animatedComposable(Screen.Splash.route) {
                SplashScreen(onAction = actions::navigateToLogin)
            }

            animatedComposable(Screen.Login.route) {
                LoginScreen(onAction = actions::navigateToHome)
            }

            animatedComposable(Screen.Home.route) {
                HomeScreen(onAction = actions::navigateFromHome, navController = navController)
            }

            animatedComposable(Screen.Detail.route) {
                DetailScreen(onAction = actions::navigateFromDetails)
            }

            animatedComposable(Screen.Search.route) {
                SearchScreen(navController = navController)
            }

            animatedComposable(Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }

            animatedComposable(Screen.Liked.route) {
                LikedScreen(navController = navController)
            }
        }
    }
}

class NavActions(private val navController: NavController) {
    fun navigateToLogin(_A: SplashScreenActions) {
        navController.navigate(Screen.Login.name) {
            popUpTo(Screen.Login.route) {
                inclusive = true
            }
        }
    }

    fun navigateToHome(_A: LoginScreenActions) {
        navController.navigate(Screen.Home.name) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    fun navigateFromHome(actions: HomeScreenActions) {
        when (actions) {
            HomeScreenActions.Details -> {
                navController.navigate(Screen.Detail.name)
            }
//            HomeScreenActions.Search -> { navController.navigate(Screen.Search.name) }
//            HomeScreenActions.Profile -> { navController.navigate(Screen.Profile.name) }
//            HomeScreenActions.Liked -> { navController.navigate(Screen.Liked.name) }
        }
    }

    fun navigateFromDetails(actions: DetailScreenActions) {
        when (actions) {
            DetailScreenActions.Back -> navController.popBackStack()
        }
    }
}