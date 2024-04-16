package com.example.hexagon_employer_list.data.repository

import com.example.hexagon_employer_list.Hexagon
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class EmployeeRepositoryImpl : EmployeeRepository {
    private val realm = Hexagon.realm
    override fun getEmployees(): Flow<List<LocalEmployee>> =
        realm.query<LocalEmployee>().asFlow().map { it.list }

    override fun getEmployeeById(employeeId: ObjectId): LocalEmployee? =
        realm.query<LocalEmployee>("_id==$0", employeeId).first().find()

    override suspend fun upsertEmployee(employee: LocalEmployee) {
        realm.writeBlocking {
            val liveEmployee =
                this.query<LocalEmployee>("_id == $0", employee.id).first().find()

            liveEmployee?.apply {
                this.city = employee.city
                this.profilePicture = employee.profilePicture
                this.name = employee.name
                this.birthDate = employee.birthDate
                this.active = employee.active
                this.document = employee.document
            } ?: copyToRealm(instance = employee, updatePolicy = UpdatePolicy.ALL)

        }
    }

    override suspend fun deleteEmployee(employee: LocalEmployee) {
        realm.writeBlocking {
            findLatest(employee)?.also {
                delete(it)
            }
        }
    }
}