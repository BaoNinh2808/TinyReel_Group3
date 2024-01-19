package com.example.myprofile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute

fun NavGraphBuilder.myProfileNavGraph(navController: NavController) {
    composable(route = DestinationRoute.MY_PROFILE_ROUTE) {
        LoggedInProfileScreen(navController)
    }

    composable(route = DestinationRoute.MY_PROFILE_SETTING_ROUTE) {
        ProfileSettingScreen(navController)
    }
}