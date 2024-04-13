package com.example.hexagon_employer_list.ui.navigation.graph

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormScreen
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormViewModel
import com.example.hexagon_employer_list.ui.components.screen.list.EmployeeListScreen
import com.example.hexagon_employer_list.ui.components.screen.list.EmployeeListViewModel
import com.example.hexagon_employer_list.ui.components.screen.splash.SplashScreen
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeForm
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeList
import com.example.hexagon_employer_list.ui.navigation.destination.Splash
import org.mongodb.kbson.ObjectId

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    composable(
        route = Splash.route
    ) {
        SplashScreen(onTimeout = {
            navController.navigate(EmployeeList.route) {
                popUpTo(Splash.route) { inclusive = true }
            }
        })
    }

    composable(
        route = EmployeeList.route
    ) {
        val viewModel = hiltViewModel<EmployeeListViewModel>()
        EmployeeListScreen(
            employeeList = viewModel.employeeList,
            onAddClick = { navController.navigate(EmployeeForm.route) },
            onEditClick = { employee -> navController.navigate("${EmployeeForm.route}?${EmployeeForm.id}=${employee._id.toHexString()}") },
            onDeleteClick = { employee -> viewModel.deleteEmployee(employee) }
        )
    }

    composable(
        route = EmployeeForm.routeWithArgs,
        arguments = EmployeeForm.arguments
    ) { navBackStack ->
        val id = navBackStack
            .arguments?.getString(EmployeeForm.id) ?: ""

        val viewModel = hiltViewModel<EmployeeFormViewModel>()

        if (id.isNotEmpty()) {
            viewModel.getEmployeeById(ObjectId(hexString = id))
        }

        EmployeeFormScreen(
            state = viewModel.uiState,
            onEvent = { event -> viewModel.onEvent(event) }
        )
    }
}