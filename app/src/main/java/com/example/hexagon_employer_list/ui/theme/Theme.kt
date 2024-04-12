package com.example.hexagon_employer_list.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HexagonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = HexagonLightColorScheme,
        typography = SecondTypography,
        shapes = shapes,
        content = content
    )
}