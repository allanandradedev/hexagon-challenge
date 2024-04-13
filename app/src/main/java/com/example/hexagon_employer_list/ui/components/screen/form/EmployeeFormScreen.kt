package com.example.hexagon_employer_list.ui.components.screen.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.template.EmployeeFormTemplate
import com.example.hexagon_employer_list.ui.components.template.LoadingTemplate
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeFormScreen(state: StateFlow<EmployeeFormState>, onEvent: (EmployeeFormEvent) -> Unit) {
    val currentState by state.collectAsState()

    when(currentState) {
        is EmployeeFormState.Error -> {

        }
        is EmployeeFormState.Loading -> {
            LoadingTemplate(text = (currentState as EmployeeFormState.Loading).message)
        }
        is EmployeeFormState.Success -> {
            EmployeeFormTemplate(
                employee = (currentState as EmployeeFormState.Success).employee,
                onEvent = onEvent
            )
        }
    }
}

sealed class EmployeeFormState {
    data class Loading(val message: String) : EmployeeFormState()
    data class Success(val employee: LocalEmployee = LocalEmployee()): EmployeeFormState()
    data class Error (val message: String): EmployeeFormState()
}

sealed interface EmployeeFormEvent {
    data class OnClick(val employee: LocalEmployee): EmployeeFormEvent
}

