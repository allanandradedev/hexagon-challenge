package com.example.hexagon_employer_list.ui.components.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.domain.use_case.CalculateAgeUseCase
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@Composable
fun EmployeeItem(employee: LocalEmployee, visualization: EmployeeVisualization, onItemClick: (LocalEmployee) -> Unit) {
    when (visualization) {
        EmployeeVisualization.LIST -> {
            val cardColors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .shadow(elevation = 2.dp, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = cardColors,
                onClick = { onItemClick.invoke(employee) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(employee.profilePicture)
                            .crossfade(true)
                            .build(),
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Nome: ${employee.name}")
                        Text(text = "Idade: ${CalculateAgeUseCase().invoke(employee.birthDate)}")
                    }
                }
            }

        }

        EmployeeVisualization.GRID -> {
            val cardColors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .shadow(elevation = 2.dp, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = cardColors,
                onClick = { onItemClick.invoke(employee) }
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(employee.profilePicture)
                            .crossfade(true)
                            .build(),
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(text = employee.name)
                        Text(text = "Idade: ${CalculateAgeUseCase().invoke(employee.birthDate)}")
                    }

                }
            }
        }
    }
}

enum class EmployeeVisualization {
    LIST, GRID
}


@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
@Composable
fun EmployeeItemPreview() {
    val employee = LocalEmployee().apply {
        this.active = true
        this.city = "Recife"
        this.name = "Allan Renan"
        this.birthDate = "27091999"
        this.profilePicture = "https://avatars.githubusercontent.com/u/86168014?v=4"
    }

    HexagonTheme {
        EmployeeItem(employee, visualization = EmployeeVisualization.GRID, onItemClick = {})
    }
}