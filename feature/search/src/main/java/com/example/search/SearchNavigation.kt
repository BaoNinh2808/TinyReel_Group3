package com.example.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.DestinationRoute.SEARCH_RESULT_ROUTE
import com.example.core.DestinationRoute.SEARCH_ROUTE
import com.example.search.resultscreen.SearchResultScreen
import com.example.search.searchscreen.SearchScreen

fun NavGraphBuilder.searchNavGraph(navController: NavController) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(navController)
    }
}

fun NavGraphBuilder.searchResultNavGraph(navController: NavController, query : String) {
    composable(route = SEARCH_RESULT_ROUTE) {
        SearchResultScreen(navController, query = query)
    }
}