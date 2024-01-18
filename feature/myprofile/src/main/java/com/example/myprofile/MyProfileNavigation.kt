package com.example.myprofile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel

fun NavGraphBuilder.myProfileNavGraph(loginViewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = DestinationRoute.MY_PROFILE_ROUTE) {
        MyProfileScreen(navController)
    }

    composable(route = DestinationRoute.MY_PROFILE_SETTING_ROUTE) {
        ProfileSettingScreen(loginViewModel, navController)
    }
}