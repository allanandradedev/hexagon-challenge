package com.example.hexagon_employer_list.ui.components.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.atom.TextAtom
import com.example.hexagon_employer_list.ui.components.organism.EmployeeListOrganism
import com.example.hexagon_employer_list.ui.components.screen.list.EmployeeListEvent
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListTemplate(
    employeeList: List<LocalEmployee>,
    onEvent: (EmployeeListEvent) -> Unit,
    onEdit: (LocalEmployee) -> Unit,
    onAdd: () -> Unit,
    onItemClick: (LocalEmployee) -> Unit
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
                        painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.hexagon_logo_dark) else painterResource(
                            id = R.drawable.hexagon_logo_light
                        ),
                        contentDescription = stringResource(R.string.hexagon_logo),
                        modifier = Modifier.height(35.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAdd,
                containerColor = MaterialTheme.colorScheme.onBackground
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Add),
                    contentDescription = stringResource(R.string.add_icon),
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
            DockedSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { active = false },
                active = active,
                onActiveChange = onActiveChange,
                placeholder = {
                    TextAtom(text = stringResource(R.string.type_employee_name))
                },
                leadingIcon = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Rounded.Search),
                        contentDescription = stringResource(R.string.search_icon)
                    )
                },
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
            ) {
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (employeeList.isNotEmpty()) {
                EmployeeListOrganism(
                    employeeList = employeeList.filter {
                        it.name.uppercase().contains(query.uppercase())
                    },
                    onItemClick = onItemClick,
                    onEdit = onEdit,
                    onAction = { onEvent.invoke(EmployeeListEvent.OnToggleActivation(it)) },
                    onDelete = { onEvent.invoke(EmployeeListEvent.OnDelete(it)) }
                )
            } else {
                WarningTemplate(
                    title = stringResource(R.string.nothing_to_show),
                    subTitle = stringResource(R.string.havent_add_employee),
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
            onEvent = {},
            onItemClick = {},
            onEdit = {},
            onAdd = {}
        )
    }
}