package com.example.hexagon_employer_list.domain.use_case

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class CalculateAgeUseCase {
    operator fun invoke(birthDate: String): Int {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dateOfBirth = LocalDate.parse(birthDate, formatter)
        val currentDate = LocalDate.now()
        val period = Period.between(dateOfBirth, currentDate)
        return period.years
    }
}