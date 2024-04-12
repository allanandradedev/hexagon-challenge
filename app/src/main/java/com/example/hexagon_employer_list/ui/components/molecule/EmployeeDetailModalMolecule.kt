package com.example.hexagon_employer_list.ui.components.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.domain.use_case.CalculateAgeUseCase
import com.example.hexagon_employer_list.ui.components.atom.ButtonAtom
import com.example.hexagon_employer_list.ui.components.atom.ButtonType
import com.example.hexagon_employer_list.ui.components.atom.TextAtom
import com.example.hexagon_employer_list.ui.theme.HexagonTheme
import kotlinx.coroutines.launch

@Composable
fun EmployeeDetailModalMolecule(
    employee: LocalEmployee,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()

    BottomModalMolecule(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            TextAtom(
                text = "Nome: ${employee.name}",
                style = MaterialTheme.typography.headlineLarge
            )
            TextAtom(
                text = "Idade: ${CalculateAgeUseCase().invoke(employee.birthDate)}",
                style = MaterialTheme.typography.headlineMedium
            )
            TextAtom(
                text = "Data de Nascimento: ${employee.birthDate}",
                style = MaterialTheme.typography.headlineMedium
            )
            TextAtom(
                text = "Cidade: ${employee.city}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(28.dp))

            ButtonAtom(
                text = "Excluir",
                onClick = {
                    scope.launch {
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                buttonType = ButtonType.OUTLINE,
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonAtom(
                text = "Editar",
                onClick = {
                    scope.launch {
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                buttonColor = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview
@Composable
fun EmployeeDetailModalPreview() {
    var showModal by remember { mutableStateOf(false) }

    val employee = LocalEmployee().apply {
        this.active = true
        this.city = "Recife"
        this.name = "Allan Renan"
        this.birthDate = "27/09/1999"
        this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
    }

    HexagonTheme {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            ButtonAtom(text = "Show modal", onClick = { showModal = true })

            if (showModal) {
                EmployeeDetailModalMolecule(employee = employee, onDismiss = {})
            }
        }


    }
}