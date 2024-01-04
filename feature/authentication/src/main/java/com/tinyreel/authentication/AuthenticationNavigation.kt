package com.tinyreel.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
//import com.tinyreel.authentication.screen.AuthenticationScreen
import com.tinyreel.authentication.screen.LoginScreen


@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.authenticationNavGraph(viewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = AUTHENTICATION_ROUTE) {
        LoginScreen(viewModel, navController)
    }
}