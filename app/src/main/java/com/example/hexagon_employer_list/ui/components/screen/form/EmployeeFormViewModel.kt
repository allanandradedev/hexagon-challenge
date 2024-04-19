package com.example.hexagon_employer_list.ui.components.screen.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_employer_list.Hexagon
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.domain.use_case.GetEmployeeByIdUseCase
import com.example.hexagon_employer_list.ui.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class EmployeeFormViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<EmployeeFormState>(EmployeeFormState.Success())
    val uiState: StateFlow<EmployeeFormState> = _state.asStateFlow()

    fun onEvent(event: EmployeeFormEvent) {
        when (event) {
            is EmployeeFormEvent.OnClick -> {
                try {
                    upsertEmployee(employee = event.employee)
                    Hexagon.navigationManager.navigate(NavigationEvent.NavigateBack)
                } catch (ex: Exception) {
                    _state.update {
                        (it as EmployeeFormState.Success).apply {
                            this.errorMessages = listOf(R.string.unexpected_error)
                        }
                    }
                }
            }
        }
    }

    fun getEmployeeById(employeeId: ObjectId) {
        viewModelScope.launch {
            _state.value = EmployeeFormState.Loading(R.string.loading_employee_data)
            val result = getEmployeeByIdUseCase(employeeId)
            result?.let {
                _state.value = EmployeeFormState.Success(it)
            }
        }
    }

    private fun upsertEmployee(employee: LocalEmployee) {
        viewModelScope.launch {
            employeeRepository.upsertEmployee(employee)
        }
    }
}

