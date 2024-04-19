package com.example.hexagon_employer_list.ui.components.screen.list

import com.example.hexagon_employer_list.data.source.local.LocalEmployee

sealed interface EmployeeListEvent {
    data class OnToggleActivation(val employee: LocalEmployee): EmployeeListEvent
    data class OnEdit(val employee: LocalEmployee): EmployeeListEvent
    data class OnDelete(val employee: LocalEmployee): EmployeeListEvent
    data object OnAdd: EmployeeListEvent
}