package com.example.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.DestinationRoute.SEARCH_RESULT_ROUTE
import com.example.core.DestinationRoute.SEARCH_ROUTE
import com.example.search.resultscreen.SearchResultScreen
import com.example.search.searchscreen.SearchScreen

fun NavGraphBuilder.searchNavGraph(navController: NavController) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(navController)
    }
}

fun NavGraphBuilder.searchResultNavGraph(navController: NavController) {
    composable(
        route = "searchResult/{query}", // Ensure this matches your route definition
        arguments = listOf(navArgument("query") { type = NavType.StringType })
    ) { backStackEntry ->
        // Retrieve the 'query' argument here
        SearchResultScreen(navController, backStackEntry)
    }
}