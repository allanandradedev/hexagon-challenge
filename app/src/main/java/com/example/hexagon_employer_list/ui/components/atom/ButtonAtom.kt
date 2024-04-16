package com.example.hexagon_employer_list.ui.components.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@Composable
fun ButtonAtom(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.DEFAULT,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp)
) {
    val defaultColors = ButtonDefaults.buttonColors(
        containerColor = buttonColor,
        contentColor = if (buttonColor == MaterialTheme.colorScheme.primary)
            MaterialTheme.colorScheme.onPrimary
        else
            MaterialTheme.colorScheme.onTertiary,
        disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary
    )

    when (buttonType) {
        ButtonType.DEFAULT -> {
            Button(
                onClick = onClick,
                colors = defaultColors,
                shape = shape,
                enabled = enabled,
                modifier = modifier.height(48.dp)
            ) {
                Text(
                    text = text,
                    style = textStyle
                )
            }
        }

        ButtonType.OUTLINE -> {
            OutlinedButton(
                onClick = onClick,
                shape = shape,
                border = BorderStroke(1.dp, buttonColor),
                modifier = modifier.height(48.dp)
            ) {
                Text(
                    text = text,
                    style = textStyle,
                    color = buttonColor
                )
            }
        }
    }
}

enum class ButtonType {
    DEFAULT,
    OUTLINE
}

@Preview(
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true
)
@Composable
fun ButtonAtomPreview() {
    HexagonTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonAtom(
                text = "Fazer Tarefa",
                onClick = {},
                buttonColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
            )
            ButtonAtom(
                text = "Fazer Tarefa",
                onClick = {},
                buttonType = ButtonType.OUTLINE,
                buttonColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}