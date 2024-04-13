package com.example.hexagon_employer_list.domain.use_case

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class FormatLocalDateUseCase {
    operator fun invoke(birthDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
        val date = LocalDate.parse(birthDate, formatter)
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return date.format(outputFormatter)
    }
}