package com.automotive.bootcamp.climate.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.automotive.bootcamp.climate.R
import com.automotive.bootcamp.climate.presentation.*
import com.automotive.bootcamp.climate.presentation.components.*

@Composable
fun ClimateScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onClose()
    }

    val vm = viewModel<ClimateViewModel>()
    ClimateView(
        modifier = modifier,
        model = vm
    )
}

@Composable
private fun ClimateView(
    modifier: Modifier = Modifier,
    model: ClimateViewModel
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xfff5fafb)),
        modifier = Modifier
            .padding(cc.padding)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(cc.padding),
            verticalArrangement = Arrangement.spacedBy(cc.padding)
        ) {
            IconWithText(
                text = stringResource(R.string.climate_title_main),
                textStyle = ClimateFontStyle.Header1.style,
                imageVector = Icons.Rounded.Home,
                iconTint = Color.Blue.copy(alpha = 0.6f),
                modifier = Modifier
                    .padding(cc.padding)
            )

            ClimateRow(
                modifier = Modifier
                    .weight(5f),
            ) {
                TemperatureCard(
                    modifier = Modifier
                        .weight(1f)
                )
                FanCard(
                    modifier = Modifier
                        .weight(1f)
                )
                ACCard(
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Row( // bottom
                modifier = Modifier
                    .weight(7f)
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(cc.padding)
            ) {
                HeatingCard(
                    modifier = Modifier.weight(1f),
                )
                PresetsCard(
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_TABLET,
    backgroundColor = 0x6264,
)
@Composable
fun ClimateViewPreview() {
    val vm = ClimateViewModel()
    ClimateView(model = vm)
}
