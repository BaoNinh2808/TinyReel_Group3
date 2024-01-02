package com.example.tinyreel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core.DestinationRoute.MY_PROFILE_ROUTE
import com.example.myprofile.myProfileNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    startDestination: String = HOME_SCREEN_ROUTE
    startDestination: String = MY_PROFILE_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
//        homeNavGraph(navController)
//        commentListingNavGraph(navController)
//        creatorProfileNavGraph(navController)
//        inboxNavGraph(navController)
//        authenticationNavGraph(navController)
//        loginEmailPhoneNavGraph(navController)
//        friendsNavGraph(navController)
        myProfileNavGraph(navController)
//        settingNavGraph(navController)
//        cameraMediaNavGraph(navController)
    }
}