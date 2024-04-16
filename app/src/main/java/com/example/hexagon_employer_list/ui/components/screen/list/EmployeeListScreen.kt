package com.example.hexagon_employer_list.ui.components.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeDetailModalMolecule
import com.example.hexagon_employer_list.ui.components.template.EmployeeListTemplate
import com.example.hexagon_employer_list.ui.components.template.LoadingTemplate
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeListScreen(
    state: StateFlow<EmployeeListState>,
    onEvent: (EmployeeListEvent) -> Unit,
    onEdit: (LocalEmployee) -> Unit,
    onAdd: () -> Unit
) {
    val currentState by state.collectAsState()

    var showDetails by remember {
        mutableStateOf(Pair<Boolean, LocalEmployee?>(false, null))
    }

    when(currentState) {
        is EmployeeListState.Loading -> {
            LoadingTemplate(text = (currentState as EmployeeListState.Loading).message)
        }
        is EmployeeListState.Success -> {
            EmployeeListTemplate(
                employeeList = (currentState as EmployeeListState.Success).employeeList,
                onItemClick = { employee -> showDetails = Pair(true, employee)},
                onEvent = onEvent,
                onEdit = onEdit,
                onAdd = onAdd
            )
        }
    }

    if (showDetails.first) {
        EmployeeDetailModalMolecule(
            employee = showDetails.second!!,
            onDismiss = { showDetails = Pair(false, null) },
            onEditClick = onEdit,
            onDeleteClick = {
                onEvent.invoke(EmployeeListEvent.OnDelete(it))
                showDetails = Pair(false, null)
            }
        )
    }


}

