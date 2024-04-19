package com.example.hexagon_employer_list.ui.components.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_employer_list.Hexagon
import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.navigation.NavigationEvent
import com.example.hexagon_employer_list.ui.navigation.destination.EmployeeForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {
    private val _state = MutableStateFlow<EmployeeListState>(
        EmployeeListState.Success(
            employeeList = emptyList()
        )
    )
    val uiState: StateFlow<EmployeeListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            employeeRepository.getEmployees().collect { employees ->
                if (_state.value is EmployeeListState.Success) {
                    _state.value =
                        (_state.value as EmployeeListState.Success).copy(employeeList = employees)
                }
            }
        }
    }

    fun onEvent(event: EmployeeListEvent) {
        when (event) {
            is EmployeeListEvent.OnDelete -> {
                deleteEmployee(event.employee)
            }

            is EmployeeListEvent.OnEdit -> {
                Hexagon.navigationManager.navigate(
                    NavigationEvent.NavigateToEmployeeForm(
                        mapOf(
                            EmployeeForm.ID to event.employee.id.toHexString()
                        )
                    )
                )
            }

            is EmployeeListEvent.OnToggleActivation -> {
                editEmployee(event.employee)
            }

            EmployeeListEvent.OnAdd -> {
                Hexagon.navigationManager.navigate(NavigationEvent.NavigateToEmployeeForm())
            }
        }
    }

    private fun deleteEmployee(localEmployee: LocalEmployee) {
        viewModelScope.launch {
            employeeRepository.deleteEmployee(localEmployee)
        }
    }

    private fun editEmployee(localEmployee: LocalEmployee) {
        viewModelScope.launch {
            employeeRepository.upsertEmployee(localEmployee)
        }
    }
}