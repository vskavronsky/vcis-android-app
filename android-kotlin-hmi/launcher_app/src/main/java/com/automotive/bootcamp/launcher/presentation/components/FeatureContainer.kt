package com.automotive.bootcamp.launcher.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.adas.presentation.screen.AdasScreen
import com.automotive.bootcamp.chargingPreferences.presentation.screen.ChargingPreferencesScreen
import com.automotive.bootcamp.status.presentation.screen.VehicleStatusScreen
import com.automotive.bootcamp.example.presentation.screen.ExampleScreen
import com.automotive.bootcamp.climate.presentation.screen.ClimateScreen
import com.automotive.bootcamp.voicesettings.presentation.screen.VoiceSettingsScreen
import com.automotive.bootcamp.userprofiles.presentation.screen.UserProfilesScreen

@Composable
fun FeaturesContainer(
    onCloseFeature: () -> Unit,
    modifier: Modifier = Modifier,
    feature: Feature = Feature.EXAMPLE_FEATURE
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (feature) {
            Feature.EXAMPLE_FEATURE -> ExampleScreen(
                onClose = onCloseFeature
            )
            
            Feature.ADAS_FEATURE -> AdasScreen(
                onClose = onCloseFeature
            )

            Feature.VEHICLE_STATUS_FEATURE -> VehicleStatusScreen(
                onClose = onCloseFeature
            )
            
            Feature.CHARGING_PREFERENCES_FEATURE -> ChargingPreferencesScreen(
                onClose = onCloseFeature
            )
            
            Feature.CLIMATE_FEATURE -> ClimateScreen(
                onClose = onCloseFeature
            )
            
            Feature.VCIS_FEATURE -> VoiceSettingsScreen(
                onClose = onCloseFeature
            )

            Feature.USER_PROFILES_FEATURE -> UserProfilesScreen(
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
        feature = Feature.EXAMPLE_FEATURE
    )
}
