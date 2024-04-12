package com.example.hexagon_employer_list.ui.components.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.hexagon_employer_list.ui.components.template.SplashScreenTemplate
import kotlinx.coroutines.delay

private const val SPLASH_WAIT_TIME: Long = 2_000

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(Unit) {
        delay(SPLASH_WAIT_TIME)
        currentOnTimeout()
    }

    SplashScreenTemplate()
}