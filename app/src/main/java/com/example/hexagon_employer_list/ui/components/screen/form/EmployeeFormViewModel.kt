package com.example.hexagon_employer_list.ui.components.screen.form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.domain.use_case.GetEmployeeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.copyFromRealm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class EmployeeFormViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
): ViewModel() {

    private val _state = MutableStateFlow<EmployeeFormState>(EmployeeFormState.Success())
    val uiState: StateFlow<EmployeeFormState> = _state.asStateFlow()

    fun onEvent(event: EmployeeFormEvent) {
        when(event) {
            is EmployeeFormEvent.OnClick -> {
                upsertEmployee(employee = event.employee)
            }
        }
    }

    fun getEmployeeById(employeeId: ObjectId) {
        viewModelScope.launch {
            _state.value = EmployeeFormState.Loading("Carregando dados do funcionário")
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