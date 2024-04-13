package com.example.hexagon_employer_list.ui.components.template

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import com.example.hexagon_employer_list.ui.components.atom.ButtonAtom
import com.example.hexagon_employer_list.ui.components.atom.TextAtom
import com.example.hexagon_employer_list.ui.components.screen.form.EmployeeFormEvent
import com.example.hexagon_employer_list.ui.components.visual_transformations.DateTransformation
import com.example.hexagon_employer_list.ui.components.visual_transformations.DocumentTransformation
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeFormTemplate(employee: LocalEmployee, onEvent: (EmployeeFormEvent) -> Unit) {
    var name by remember {
        mutableStateOf(employee.name)
    }
    var birthDate by remember {
        mutableStateOf(employee.birthDate)
    }
    var document by remember {
        mutableStateOf(employee.document)
    }
    var city by remember {
        mutableStateOf(employee.city)
    }
    var active by remember {
        mutableStateOf(employee.active)
    }
    var profilePicture: Uri? by remember {
        mutableStateOf(employee.profilePicture.toUri())
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> profilePicture = uri }
    )

    val onNameChange = { text: String -> name = text }
    val onBirthDateChange = { text: String -> if (text.length <= 8) birthDate = text }
    val onDocumentChange = { text: String -> if (text.length <= 14) document = text }
    val onCityChange = { text: String -> city = text }

    val enabled =
        name.isNotEmpty() && birthDate.isNotEmpty() && document.isNotEmpty() && city.isNotEmpty()

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
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(profilePicture)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    placeholder = rememberVectorPainter(image = Icons.Filled.AccountCircle),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextAtom(text = "Selecione a imagem de perfil.", style = MaterialTheme.typography.headlineMedium)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                label = {
                    TextAtom(text = "Nome")
                },
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                label = {
                    TextAtom(text = "Data de Nascimento")
                },
                value = birthDate,
                onValueChange = onBirthDateChange,
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = DateTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                label = {
                    TextAtom(text = "CPF")
                },
                value = document,
                onValueChange = onDocumentChange,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = DocumentTransformation(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                label = {
                    TextAtom(text = "Cidade")
                },
                value = city,
                onValueChange = onCityChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextAtom(
                    text = "Ativo:",
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = active,
                    onCheckedChange = { active = !active }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ButtonAtom(
                text = "Concluir",
                onClick = {
                    onEvent(
                        EmployeeFormEvent.OnClick(
                            LocalEmployee().apply {
                                this._id = employee._id
                                this.name = name
                                this.city = city
                                this.birthDate = birthDate
                                this.document = document
                                this.active = active
                                this.profilePicture = profilePicture.toString()
                            }
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled
            )
        }
    }
}

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
@Composable
fun EmployeeFormTemplatePreview() {
    HexagonTheme {
        EmployeeFormTemplate(employee = LocalEmployee(), onEvent = {})
    }
}