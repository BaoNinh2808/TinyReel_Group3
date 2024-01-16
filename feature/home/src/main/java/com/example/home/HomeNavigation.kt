package com.example.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel

//fun NavGraphBuilder.homeNavGraph(navController: NavController) {
//    composable(route = HOME_SCREEN_ROUTE) {
//        HomeScreen(navController)
//    }
//}

fun NavGraphBuilder.homeNavGraph(viewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = HOME_SCREEN_ROUTE) {
        HomeScreen(viewModel, navController)
    }
}