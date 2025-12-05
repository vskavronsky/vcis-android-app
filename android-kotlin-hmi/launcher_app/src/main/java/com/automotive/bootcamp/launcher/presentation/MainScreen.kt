package com.automotive.bootcamp.launcher.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.launcher.presentation.components.Feature
import com.automotive.bootcamp.launcher.presentation.components.FeaturesContainer
import com.automotive.bootcamp.launcher.presentation.components.FeaturesList
import compose_theme.theme.IntelliAutoHMITheme
import compose_theme.theme.MintPrimaryDark
import compose_theme.theme.MintPrimaryLight

@Composable
fun MainScreen() {
    val gradient = Brush.linearGradient(
        colors = listOf(
            MintPrimaryLight, MintPrimaryDark,
        ), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {

        var selectedFeature by rememberSaveable { mutableStateOf(Feature.MAIN_SCREEN_FEATURE) }

        FeaturesList(
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight(),
            onFeatureClick = { selectedFeature = it },
            selected = selectedFeature,
        )

        FeaturesContainer(
            onCloseFeature = { selectedFeature = Feature.MAIN_SCREEN_FEATURE },
            modifier = Modifier
                .weight(2f)
                .fillMaxSize(),
            feature = selectedFeature
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
@Composable
fun ScreenPreview() {
    IntelliAutoHMITheme {
        MainScreen()
    }
}