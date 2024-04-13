package com.example.hexagon_employer_list.data.repository

import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface EmployeeRepository {
    fun getEmployees(): Flow<List<LocalEmployee>>
    fun getEmployeeById(employeeId: ObjectId): LocalEmployee?
    suspend fun upsertEmployee(employee: LocalEmployee)

    suspend fun deleteEmployee(employee: LocalEmployee)
}