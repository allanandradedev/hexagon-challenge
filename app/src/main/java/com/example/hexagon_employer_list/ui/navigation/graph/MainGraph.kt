package com.example.hexagon_employer_list.ui.navigation.graph

import androidx.compose.material3.Scaffold
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hexagon_employer_list.ui.components.screen.splash.SplashScreen
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeForm
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeList
import com.example.hexagon_employer_list.ui.navigation.destination.Splash

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(
        route = Splash.route
    ) {
        SplashScreen(onTimeout = {
            navController.navigate(EmployeeList.route) {
                popUpTo(Splash.route) {inclusive = true}
            }
        })
    }

    composable(
        route = EmployeeList.route
    ) {

    }

    composable(
        route = EmployeeForm.route
    ) {

    }
}