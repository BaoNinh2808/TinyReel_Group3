package com.tinyreel.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.tinyreel.authentication.AuthenticationScreen


@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.authenticationNavGraph(navController: NavController) {
    composable(route = AUTHENTICATION_ROUTE) {
        AuthenticationScreen(navController)
    }
}