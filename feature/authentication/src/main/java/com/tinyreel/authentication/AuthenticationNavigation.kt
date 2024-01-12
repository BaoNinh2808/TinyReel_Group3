package com.tinyreel.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.example.core.DestinationRoute.SIGNUP_ROUTE
//import com.tinyreel.authentication.screen.AuthenticationScreen
import com.tinyreel.authentication.screen.LoginScreen
import com.tinyreel.authentication.screen.SignupScreen


@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.authenticationNavGraph(viewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = AUTHENTICATION_ROUTE) {
        LoginScreen(viewModel, navController)
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.signUpNavGraph(viewModel: LoginWithEmailPhoneViewModel, navController: NavController) {
    composable(route = SIGNUP_ROUTE) {
        SignupScreen(viewModel, navController)
    }
}