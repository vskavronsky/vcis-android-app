package com.example.main_screen.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose_theme.theme.MintSecondary

@Composable
fun MainScreen(
    onClose: () -> Unit, modifier: Modifier = Modifier
) {
    MainView(
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun MainView(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier, color = MintSecondary, shape = RoundedCornerShape(
            topStart = 40.dp, topEnd = 40.dp
        )
    ) {}
}
