package com.example.hexagon_employer_list.domain.use_case

import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import io.realm.kotlin.ext.copyFromRealm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

class GetEmployeeByIdUseCase(
    private val employeeRepository: EmployeeRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(employeeId: ObjectId): LocalEmployee? =
        withContext(defaultDispatcher) {
            return@withContext employeeRepository.getEmployeeById(employeeId)
        }
}