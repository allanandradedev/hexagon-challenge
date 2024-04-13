package com.example.hexagon_employer_list.domain.use_case

import com.example.hexagon_employer_list.data.source.local.LocalEmployee

class FilterEmployeeUseCase {
    operator fun invoke(employeeList: List<LocalEmployee>, query: String): List<LocalEmployee> {
        return employeeList
    }
}