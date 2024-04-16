package com.example.hexagon_employer_list.ui.components.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hexagon_employer_list.ui.theme.HexagonTheme
import com.example.hexagon_employer_list.ui.components.visual_transformations.DocumentTransformation

@Composable
fun TextFieldAtom(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType = TextFieldType.DEFAULT,
    isError: Boolean = false,
    supportingText: String = "",
    placeholder: String = "",
    prefix: String = "",
    textAlign: TextAlign = TextAlign.Start,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val shape = MaterialTheme.shapes.extraSmall
    val textFieldStyle = MaterialTheme.typography.headlineLarge
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
        cursorColor = MaterialTheme.colorScheme.secondary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
        focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        focusedPlaceholderColor = MaterialTheme.colorScheme.outlineVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.outlineVariant,
        errorContainerColor = MaterialTheme.colorScheme.background,
        errorTrailingIconColor = MaterialTheme.colorScheme.onBackground
    )
    Column {
        TextAtom(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = modifier,
            textAlign = textAlign
        )

        when (textFieldType) {
            TextFieldType.DEFAULT -> {
                TextField(
                    value = value,
                    onValueChange = { text ->
                        onValueChange(text.filter { it.isLetterOrDigit() })
                    },
                    isError = isError,
                    singleLine = true,
                    shape = shape,
                    colors = colors,
                    modifier = modifier,
                    textStyle = textFieldStyle,
                    prefix = {
                        TextAtom(text = prefix, style = textFieldStyle)
                    },
                    placeholder = {
                        TextAtom(text = placeholder, style = textFieldStyle)
                    },
                    supportingText = {
                        SupportingText(isError = isError, text = supportingText)
                    },
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions
                )
            }

            TextFieldType.DOCUMENT -> {
                TextField(
                    value = value,
                    onValueChange = { text ->
                        if (text.length <= 14) {
                            onValueChange(text.filter { it.isLetterOrDigit() })
                        }
                    },
                    isError = isError,
                    singleLine = true,
                    shape = shape,
                    colors = colors,
                    modifier = modifier,
                    textStyle = textFieldStyle,
                    visualTransformation = DocumentTransformation(),
                    placeholder = {
                        TextAtom(text = placeholder, style = textFieldStyle)
                    },
                    supportingText = {
                        SupportingText(isError = isError, text = supportingText)
                    },
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions
                )
            }
        }
    }
}

@Composable
private fun SupportingText(
    isError: Boolean,
    text: String
) {
    Row(
        modifier = Modifier.height(18.dp),
        verticalAlignment = Alignment.Top
    ) {
        if (isError)
            TextAtom(
                text = text,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 8.sp
                )
            )
    }
}

enum class TextFieldType {
    DEFAULT,
    DOCUMENT
}

@Preview(device = "spec:width=720px,height=1280px,dpi=320", showSystemUi = true)
@Composable
fun TextFieldAtomPreview() {
    var value by remember { mutableStateOf("") }
    val onValueChange = { text: String -> value = text }

    val localModifier = Modifier.fillMaxWidth()

    HexagonTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            TextFieldAtom(
                title = "CPF/CNPJ do Titular",
                value = value,
                onValueChange = onValueChange,
                textFieldType = TextFieldType.DOCUMENT,
                modifier = localModifier,
            )
            TextFieldAtom(
                title = "Algum Texto",
                value = value,
                onValueChange = onValueChange,
                textFieldType = TextFieldType.DEFAULT,
                modifier = localModifier,
            )
        }
    }
}
