package com.example.hexagon_employer_list.ui.components.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeDetailModalMolecule
import com.example.hexagon_employer_list.ui.components.template.EmployeeListTemplate
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EmployeeListScreen(
    employeeList: StateFlow<List<LocalEmployee>>,
    onEditClick: (LocalEmployee) -> Unit,
    onDeleteClick: (LocalEmployee) -> Unit,
    onAddClick: () -> Unit
) {
    val currentEmployees by employeeList.collectAsStateWithLifecycle()

    var showDetail by remember {
        mutableStateOf(Pair(false, LocalEmployee()))
    }

    EmployeeListTemplate(
        employeeList = currentEmployees,
        onSearch = {},
        onItemClick = {
            showDetail = Pair(true, it)
        },
        onAddClick = onAddClick,
    )

    if (showDetail.first) {
        EmployeeDetailModalMolecule(
            employee = showDetail.second,
            onDismiss = { showDetail = Pair(false, LocalEmployee())},
            onEditClick = onEditClick,
            onDeleteClick = {
                onDeleteClick.invoke(it)
                showDetail = Pair(false, LocalEmployee())
            }
        )
    }
}