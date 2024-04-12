package com.example.hexagon_employer_list.ui.components.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeDetailModalMolecule
import com.example.hexagon_employer_list.ui.components.organism.EmployeeListOrganism
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@Composable
fun EmployeeListTemplate(
    employeeList: List<LocalEmployee>,
) {
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (employeeList.isNotEmpty()) {
                EmployeeListOrganism(
                    employeeList = employeeList,
                    onItemClick = {
                    }
                )
            }


        }
    }


}

@Preview
@Composable
fun EmployeeListPreview() {
    val employeeList = listOf(
        LocalEmployee().apply {
            this.active = true
            this.city = "Recife"
            this.name = "Allan Renan"
            this.birthDate = "27/09/1999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        },
        LocalEmployee().apply {
            this.active = true
            this.city = "Recife"
            this.name = "Maria Stephanie"
            this.birthDate = "22/04/2001"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        }
    )

    HexagonTheme {
        EmployeeListTemplate(
            employeeList = employeeList
        )
    }
}