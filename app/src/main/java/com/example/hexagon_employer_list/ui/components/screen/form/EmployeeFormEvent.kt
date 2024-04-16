package com.example.hexagon_employer_list.ui.components.screen.form

import com.example.hexagon_employer_list.data.source.local.LocalEmployee

sealed interface EmployeeFormEvent {
    data class OnClick(val employee: LocalEmployee): EmployeeFormEvent
}