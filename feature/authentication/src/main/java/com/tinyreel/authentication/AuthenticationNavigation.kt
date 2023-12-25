package com.tinyreel.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.example.core.DestinationRoute.AUTHENTICATION_ROUTE
import com.puskal.authentication.AuthenticationScreen


@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.authenticationNavGraph(navController: NavController) {
    bottomSheet(route = AUTHENTICATION_ROUTE) {
        AuthenticationScreen(navController)
    }
}