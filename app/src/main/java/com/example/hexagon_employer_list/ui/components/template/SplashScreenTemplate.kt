package com.example.hexagon_employer_list.ui.components.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagon_employer_list.R
import com.example.hexagon_employer_list.ui.theme.HexagonTheme

@Composable
fun SplashScreenTemplate() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.hexagon_logo),
            contentDescription = "Brand image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(150.dp)
        )
    }
}

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420")
@Composable
fun SplashScreenPreview() {
    HexagonTheme {
        SplashScreenTemplate()
    }
}