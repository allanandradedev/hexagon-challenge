package com.example.hexagon_employer_list.ui.navigation.destination

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
}