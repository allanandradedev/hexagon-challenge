package com.example.hexagon_employer_list.ui.navigation

import com.example.hexagon_employer_list.ui.navigation.destination.Destination
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeForm

sealed class NavigationEvent(
    val destination: Destination? = null,
    open var params: Map<String, String>? = null
) {
    data object NavigateBack : NavigationEvent()
    data class NavigateToEmployeeForm(override var params: Map<String, String>? = null) :
        NavigationEvent(EmployeeForm, params)
}