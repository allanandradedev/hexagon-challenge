package com.example.hexagon_employer_list.ui.components.screen.form

sealed interface EmployeeFormViewModelEvent {
    data object OnUpsertFinish: EmployeeFormViewModelEvent
}