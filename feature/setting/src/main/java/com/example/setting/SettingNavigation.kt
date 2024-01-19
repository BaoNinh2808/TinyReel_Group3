package com.example.setting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel

fun NavGraphBuilder.settingNavGraph(loginViewModel : LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = DestinationRoute.SETTING_ROUTE) {
        SettingScreen(loginViewModel, navController)
    }
}