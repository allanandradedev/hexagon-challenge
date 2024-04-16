package com.example.hexagon_employer_list.ui.components.molecule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.domain.use_case.CalculateAgeUseCase
import com.example.hexagon_employer_list.ui.theme.HexagonTheme


@Composable
fun EmployeeItem(
    employee: LocalEmployee,
    onItemClick: (LocalEmployee) -> Unit,
    onDelete: (LocalEmployee) -> Unit,
    onEdit: (LocalEmployee) -> Unit,
    onAction: (LocalEmployee) -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(employee.profilePicture.toUri())
            .build()
    )

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            animationSpec = tween(250),
            initialOffsetX = {
                -it
            }
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            onClick = { onItemClick.invoke(employee) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.profile_picture),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(height = 100.dp, width = 80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = employee.name, style = MaterialTheme.typography.headlineMedium)
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Filled.Clear),
                            contentDescription = stringResource(R.string.delete_icon),
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onDelete.invoke(employee) }
                        )
                    }
                    Row {
                        Text(
                            text = "${stringResource(id = R.string.age)}: ${
                                CalculateAgeUseCase().invoke(
                                    employee.birthDate
                                )
                            }"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${stringResource(id = R.string.city)}: ${employee.city}")
                    }
                    Text(
                        text = "${stringResource(R.string.situation)}: ${
                            if (employee.active) stringResource(
                                R.string.active
                            ) else stringResource(R.string.inactive)
                        }",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { onEdit.invoke(employee) },
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            shape = RectangleShape,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.edit),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = {
                                val currentEmployee = LocalEmployee().apply {
                                    this.id = employee.id
                                    this.name = employee.name
                                    this.city = employee.city
                                    this.birthDate = employee.birthDate
                                    this.document = employee.document
                                    this.profilePicture = employee.profilePicture
                                    this.active = !employee.active
                                }
                                onAction.invoke(currentEmployee)
                            },
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = if (employee.active) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            ),
                            shape = RectangleShape,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(
                                1.dp,
                                if (employee.active) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = if (employee.active) stringResource(R.string.deactivate) else stringResource(
                                    R.string.activate
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
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
        EmployeeItem(employee, onItemClick = {}, onAction = {}, onDelete = {}, onEdit = {})
    }
}