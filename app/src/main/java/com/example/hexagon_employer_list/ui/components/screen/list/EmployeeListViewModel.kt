package com.example.hexagon_employer_list.ui.components.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
): ViewModel() {
    private val _employeeList = employeeRepository.getEmployees()
    val employeeList = _employeeList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun deleteEmployee(localEmployee: LocalEmployee) {
        viewModelScope.launch {
            employeeRepository.deleteEmployee(localEmployee)
        }
    }
}