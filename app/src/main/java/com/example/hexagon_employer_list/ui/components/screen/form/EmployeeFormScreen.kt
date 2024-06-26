package com.example.hexagon_employer_list.ui.components.screen.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.hexagon_employer_list.ui.components.template.EmployeeFormTemplate
import com.example.hexagon_employer_list.ui.components.template.LoadingTemplate
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeFormScreen(state: StateFlow<EmployeeFormState>, onEvent: (EmployeeFormEvent) -> Unit) {
    val currentState by state.collectAsState()

    when(currentState) {
        is EmployeeFormState.Loading -> {
            LoadingTemplate(text = stringResource((currentState as EmployeeFormState.Loading).message))
        }
        is EmployeeFormState.Success -> {
            EmployeeFormTemplate(
                state = (currentState as EmployeeFormState.Success),
                onEvent = onEvent
            )
        }
    }
}

