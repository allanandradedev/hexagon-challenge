package com.example.hexagon_employer_list.ui.components.screen.list

import com.example.hexagon_employer_list.data.source.local.LocalEmployee

sealed class EmployeeListState {
    data class Loading(val message: String) : EmployeeListState()
    data class Success(val employeeList: List<LocalEmployee>): EmployeeListState()
}