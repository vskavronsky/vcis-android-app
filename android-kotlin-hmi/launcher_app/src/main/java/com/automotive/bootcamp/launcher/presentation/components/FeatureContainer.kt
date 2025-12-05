package com.automotive.bootcamp.launcher.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.voicesettings.presentation.screen.VoiceSettingsScreen
import com.example.main_screen.presentation.screen.MainScreen

@Composable
fun FeaturesContainer(
    onCloseFeature: () -> Unit,
    modifier: Modifier = Modifier,
    feature: Feature = Feature.MAIN_SCREEN_FEATURE
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (feature) {
            Feature.MAIN_SCREEN_FEATURE -> MainScreen(
                onClose = onCloseFeature
            )

            Feature.VCIS_FEATURE -> VoiceSettingsScreen(
                onClose = onCloseFeature
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeaturesContainerPreview() {
    FeaturesContainer(onCloseFeature = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FeaturesContainerNotImplementedPreview() {
    FeaturesContainer(
        onCloseFeature = {},
        feature = Feature.MAIN_SCREEN_FEATURE
    )
}
