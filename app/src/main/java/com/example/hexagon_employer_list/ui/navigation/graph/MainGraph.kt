package com.example.hexagon_employer_list.ui.navigation.graph

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormScreen
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormViewModel
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormViewModelEvent
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
            state = viewModel.uiState,
            onEvent = { event -> viewModel.onEvent(event) },
            onEdit = { employee -> navController.navigate("${EmployeeForm.route}?${EmployeeForm.ID}=${employee.id.toHexString()}") },
            onAdd = { navController.navigate(EmployeeForm.route) }
        )
    }

    composable(
        route = EmployeeForm.routeWithArgs,
        arguments = EmployeeForm.arguments
    ) { navBackStack ->
        val id = navBackStack
            .arguments?.getString(EmployeeForm.ID) ?: ""

        val viewModel = hiltViewModel<EmployeeFormViewModel>()
        val currentEvent by viewModel.event.collectAsState()

        if (id.isNotEmpty()) {
            viewModel.getEmployeeById(ObjectId(hexString = id))
        }

        DisposableEffect(key1 = currentEvent) {
            currentEvent.let {
                if (it is EmployeeFormViewModelEvent.OnUpsertFinish) {
                    navController.navigateUp()
                }
            }

            onDispose {
                viewModel.resetEvent()
            }
        }

        EmployeeFormScreen(
            state = viewModel.uiState,
            onEvent = { event -> viewModel.onEvent(event) }
        )
    }
}