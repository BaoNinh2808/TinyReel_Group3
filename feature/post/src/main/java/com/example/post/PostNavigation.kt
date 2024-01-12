package com.example.post

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute.POST_ROUTE

fun NavGraphBuilder.postNavGraph(navController: NavController) {
    composable(route = POST_ROUTE) {
        PostScreen(navController)
    }
}