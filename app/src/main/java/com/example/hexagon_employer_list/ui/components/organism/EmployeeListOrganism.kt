package com.example.hexagon_employer_list.ui.components.organism

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.molecule.EmployeeItem
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeListOrganism(
    employeeList: List<LocalEmployee>,
    onItemClick: (LocalEmployee) -> Unit,
    onEdit: (LocalEmployee) -> Unit,
    onAction: (LocalEmployee) -> Unit,
    onDelete: (LocalEmployee) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxHeight()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(350.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(employeeList) {
                EmployeeItem(
                    employee = it,
                    onItemClick = onItemClick,
                    onDelete = onDelete,
                    onEdit = onEdit,
                    onAction = onAction
                )
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
            this.birthDate = "27091999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        },
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
            this.name = "Allan Renan"
            this.birthDate = "27091999"
            this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
        }
    )
    HexagonTheme {
        EmployeeListOrganism(
            employeeList = employeeList,
            onItemClick = {},
            onEdit = {},
            onAction = {},
            onDelete = {}
        )
    }
}
