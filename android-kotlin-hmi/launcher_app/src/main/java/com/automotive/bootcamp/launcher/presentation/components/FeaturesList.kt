package com.automotive.bootcamp.launcher.presentation.components

import FeatureButton
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.launcher.R
import compose_theme.theme.MintPrimaryDark
import compose_theme.theme.MintPrimaryLight

@Composable
fun FeaturesList(
    modifier: Modifier = Modifier, onFeatureClick: (Feature) -> Unit, selected: Feature
) {
    val gradientBg = Brush.linearGradient(
        colors = listOf(
            MintPrimaryLight,
            MintPrimaryDark,
        ), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f)
    )

    Column(
        modifier = modifier
            .background(brush = gradientBg, shape = RoundedCornerShape(10)),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.intelliauto_text),
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.afacad_flux_variable_font_slnt_wght)),
                color = Color.White,
            )
            Image(
                painter = painterResource(id = R.drawable.line_1),
                contentDescription = "Separator Line",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = true)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Feature.entries.forEach {
                FeatureButton(
                    it, onClick = { onFeatureClick(it) }, isSelected = selected == it
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    widthDp = 200,
    heightDp = 800,
    backgroundColor = 0x6264,
)
@Composable
fun GreetingPreview() {
    FeaturesList(onFeatureClick = {}, selected = Feature.EXAMPLE_FEATURE)
}

enum class Feature(@StringRes val resId: Int) {
    EXAMPLE_FEATURE(R.string.example_feature),
    ADAS_FEATURE(R.string.adas_feature),
    CHARGING_PREFERENCES_FEATURE(R.string.charging_preferences_feature),
    VEHICLE_STATUS_FEATURE(R.string.system_status),
    CLIMATE_FEATURE(R.string.climate_control),
    VCIS_FEATURE(R.string.vcis_feature),
    USER_PROFILES_FEATURE(R.string.user_profiles_feature),
}
