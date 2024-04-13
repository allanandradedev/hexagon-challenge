package com.example.hexagon_employer_list.ui.navigation.destination

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Destination {
    val route: String
}

data object Splash: Destination {
    override val route: String
        get() = "splash-screen"
}

data object EmployeeList: Destination {
    override val route: String
        get() = "employee"
}

data object EmployeeForm: Destination {
    override val route: String
        get() = "employee-form"
    const val id = "id"
    val routeWithArgs = "${route}?$id={$id}"
    val arguments = listOf(
        navArgument(id) {
            nullable = true
            type = NavType.StringType
        }
    )
}