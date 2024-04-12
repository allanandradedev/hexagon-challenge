package com.example.hexagon_employer_list.ui.components.organism

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeItem
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeVisualization
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmployeeListOrganism(employeeList: List<LocalEmployee>, onItemClick: (LocalEmployee) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        var visualization: EmployeeVisualization by remember {
            mutableStateOf(EmployeeVisualization.LIST)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Escolha como visualizar:")
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_list_view),
                    contentDescription = "List View Icon",
                    modifier = Modifier.clickable { visualization = EmployeeVisualization.LIST },
                    tint = if (visualization == EmployeeVisualization.LIST) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_grid_view),
                    contentDescription = "Grid View Icon",
                    modifier = Modifier.clickable { visualization = EmployeeVisualization.GRID },
                    tint = if (visualization == EmployeeVisualization.GRID) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (visualization) {
            EmployeeVisualization.LIST -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(employeeList) {
                        EmployeeItem(employee = it, visualization = EmployeeVisualization.LIST, onItemClick = onItemClick)
                    }
                }
            }

            EmployeeVisualization.GRID -> {
                FlowRow(
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    employeeList.forEach {
                        EmployeeItem(employee = it, visualization = EmployeeVisualization.GRID, onItemClick)
                    }
                }
            }
        }


    }
}

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
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
            this.name = "Allan Renan"
            this.birthDate = "27/09/1999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        },
        LocalEmployee().apply {
            this.active = true
            this.city = "Recife"
            this.name = "Allan Renan"
            this.birthDate = "27/09/1999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        }
    )
    HexagonTheme {
        EmployeeListOrganism(employeeList = employeeList, onItemClick = {})
    }
}
