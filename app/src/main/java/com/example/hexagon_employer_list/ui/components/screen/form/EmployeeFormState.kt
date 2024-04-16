package com.example.hexagon_employer_list.ui.components.screen.form

import com.example.hexagon_employer_list.data.source.local.LocalEmployee

sealed class EmployeeFormState {
    data class Loading(val message: Int) : EmployeeFormState()
    data class Success(val employee: LocalEmployee = LocalEmployee(), var errorMessages: List<Int> = emptyList()): EmployeeFormState()
}