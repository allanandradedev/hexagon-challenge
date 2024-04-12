package com.example.hexagon_employer_list.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hexagon_employer_list.ui.navigation.destination.Splash
import com.example.hexagon_employer_list.ui.navigation.graph.mainGraph

@Composable
fun MainNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Splash.route
    ) {
        mainGraph(navHostController)
    }
}