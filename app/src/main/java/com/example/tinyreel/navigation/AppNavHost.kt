package com.example.tinyreel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.camera.cameraMediaNavGraph
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.example.home.homeNavGraph
import com.tinyreel.authentication.authenticationNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeNavGraph(navController)
//        commentListingNavGraph(navController)
//        creatorProfileNavGraph(navController)
//        inboxNavGraph(navController)
        authenticationNavGraph(navController)
//        loginEmailPhoneNavGraph(navController)
//        friendsNavGraph(navController)
//        myProfileNavGraph(navController)
//        settingNavGraph(navController)
        cameraMediaNavGraph(navController)
    }
}