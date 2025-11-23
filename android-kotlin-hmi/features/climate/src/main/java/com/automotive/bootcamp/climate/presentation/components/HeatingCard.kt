package com.automotive.bootcamp.climate.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.climate.R
import com.automotive.bootcamp.climate.presentation.*

@Composable
fun HeatingCard(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = getRandomColor()),
        modifier = modifier.fillMaxSize()
    ) {
        ClimateColumn(
            modifier = Modifier.padding(cc.padding)
        ) {
            IconWithText(
                text = stringResource(R.string.climate_title_heating_card),
                textStyle = ClimateFontStyle.Header2.style,
                imageVector = Icons.Rounded.Favorite
            )

            data class SeatHeating(
                val titleR: Int,
                val intencity: Int,
                val action: () -> Unit
            )

            val seats = listOf(
                SeatHeating(
                    R.string.climate_label_seat_heat_driver,
                    1,
                    {}
                ),
                SeatHeating(
                    R.string.climate_label_seat_heat_passanger,
                    1,
                    {}
                )
            )
            ClimateRow(
                modifier = Modifier.weight(5f),
                horizontalArrangement = Arrangement.spacedBy(cc.padding)
            ) {
                seats.forEach {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ) {
                        ClimateColumn(
                            modifier = Modifier.padding(cc.padding)
                        ) {
                            IconWithText(
                                text = stringResource(R.string.climate_label_seat_heat_driver),
                                textStyle = ClimateFontStyle.Header2.style,
                                imageVector = Icons.Rounded.AccountBox
                            )

                            val intencities = listOf(
                                R.string.climate_radio_seat_heat_off,
                                R.string.climate_radio_seat_heat_low,
                                R.string.climate_radio_seat_heat_high
                            )
                            val (selectedSource, onOptionSelected) = remember {
                                mutableStateOf(intencities[0])
                            }

                            ClimateRow(modifier.weight(1f)) {
                                intencities.forEach {
                                    val isSelected = selectedSource == it
                                    ElevatedCard(
                                        enabled = true,
                                        onClick = { onOptionSelected(it) },
                                        colors = if (isSelected) CardDefaults.cardColors(
                                            containerColor = getRandomColor()
                                        ) else CardDefaults.cardColors(),
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                    ) {
                                        ClimateLabel(
                                            text = stringResource(it)
                                        )
                                    }
                                }
                            }
                            IconWithText(
                                text = stringResource(
                                    R.string.climate_label_current_seat_heat,
                                    stringResource(selectedSource)
                                ),
                                textStyle = ClimateFontStyle.Regular.style,
                                imageVector = Icons.Rounded.LocationOn
                            )
                        }
                    }
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                modifier = modifier
                    .fillMaxSize()
                    .weight(2f)
            ) {
                var heatingEnabled by remember { mutableStateOf(false) }

                ClimateRow(
                    modifier = Modifier.padding(cc.padding).fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconWithText(
                        text = stringResource(R.string.climate_toggle_steering_heat),
                        textStyle = ClimateFontStyle.Header3.style,
                        imageVector = Icons.Rounded.Settings,
                        modifier = Modifier.weight(1f)

                    )
                    Switch(
                        enabled = true,
                        checked = heatingEnabled,
                        onCheckedChange = {
                            heatingEnabled = !heatingEnabled
                        },
                        colors = SwitchDefaults.colors(
//                            checkedThumbColor = getRandomColor(),
                            checkedTrackColor = getRandomColor(),
//                            uncheckedThumbColor = getRandomColor(),
                            uncheckedTrackColor = getRandomColor()
                        ),
                        modifier = Modifier.align(Alignment.Top)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x6264,
    device = "spec:width=1080px,height=1200px,dpi=480,orientation=landscape",
)
@Composable
fun SkinHeatingPreview() {
    HeatingCard()
}
