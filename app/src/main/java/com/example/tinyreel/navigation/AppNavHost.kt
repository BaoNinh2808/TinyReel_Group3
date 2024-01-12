package com.example.tinyreel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.camera.cameraMediaNavGraph
import com.example.core.DestinationRoute.CAMERA_ROUTE
import com.example.core.DestinationRoute.HOME_SCREEN_ROUTE
import com.example.core.DestinationRoute.MY_PROFILE_ROUTE
import com.example.core.DestinationRoute.POST_ROUTE
import com.example.home.homeNavGraph
import com.example.myprofile.myProfileNavGraph
import com.example.post.postNavGraph
import com.tinyreel.authentication.LoginWithEmailPhoneViewModel
import com.tinyreel.authentication.authenticationNavGraph
import com.tinyreel.authentication.signUpNavGraph

@Composable
fun AppNavHost(
    loginViewModel: LoginWithEmailPhoneViewModel,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = AUTHENTICATION_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeNavGraph(navController)
        postNavGraph(navController)
//        commentListingNavGraph(navController)
//        creatorProfileNavGraph(navController)
//        inboxNavGraph(navController)
        authenticationNavGraph(loginViewModel, navController)
        signUpNavGraph(loginViewModel, navController)
//        loginEmailPhoneNavGraph(navController)
//        friendsNavGraph(navController)
        myProfileNavGraph(navController)
//        settingNavGraph(navController)
        cameraMediaNavGraph(navController)
    }
}