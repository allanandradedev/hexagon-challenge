package com.example.hexagon_employer_list.ui.components.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.atom.TextAtom
import com.example.hexagon_employer_list.ui.components.organism.EmployeeListOrganism
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListTemplate(
    employeeList: List<LocalEmployee>,
    onSearch: (String) -> Unit,
    onItemClick: (LocalEmployee) -> Unit,
    onAddClick: () -> Unit
) {
    var query by remember {
        mutableStateOf("")
    }

    var active by remember {
        mutableStateOf(false)
    }

    val onQueryChange = { text: String -> query = text }
    val onActiveChange = { option: Boolean -> active = option }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.hexagon_logo),
                        contentDescription = "Hexagon Logo"
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.onBackground
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Add),
                    contentDescription = "Add icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(vertical = 16.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                active = active,
                onActiveChange = onActiveChange,
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Rounded.Search),
                        contentDescription = "Search Icon"
                    )
                },
                placeholder = {
                    TextAtom(text = "Busque aqui o que está procurando")
                },
                modifier = Modifier.height(45.dp)
            ) {}
            Spacer(modifier = Modifier.height(8.dp))
            if (employeeList.isNotEmpty()) {
                EmployeeListOrganism(
                    employeeList = employeeList,
                    onItemClick = onItemClick

                )
            } else {
                WarningTemplate(
                    title = "Não há nada para exibir",
                    subTitle = "Você ainda não adicionou nenhum funcionário, adicione algum e ele será exibido aqui.",
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
            this.birthDate = "27091999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        },
        LocalEmployee().apply {
            this.active = true
            this.city = "Recife"
            this.name = "Maria Stephanie"
            this.birthDate = "22042001"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        },
        LocalEmployee().apply {
            this.active = true
            this.city = "Recife"
            this.name = "Anthony Robert"
            this.birthDate = "09062008"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        }
    )

    HexagonTheme {
        EmployeeListTemplate(
            employeeList = employeeList,
            onSearch = {},
            onItemClick = {},
            onAddClick = {}
        )
    }
}