package com.automotive.bootcamp.climate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.automotive.bootcamp.climate.presentation.ClimateFontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.climate.R
import com.automotive.bootcamp.climate.presentation.cc

@Composable
fun ACCard(
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
                text = stringResource(R.string.climate_title_ac_card),
                textStyle = ClimateFontStyle.Header2.style,
                imageVector = Icons.Rounded.Settings
            )

            ElevatedCard(
                colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(5f),
            ) {
                ClimateRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(cc.padding),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    var accEnabled by remember { mutableStateOf(false) }
                    IconWithText(
                        text = stringResource(R.string.climate_toggle_ac),
                        textStyle = ClimateFontStyle.Header3.style,
                        imageVector = Icons.Rounded.Settings,
                        iconTint = Color.Blue.copy(alpha = 0.6f),
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = getRandomColor(),
//                            checkedTrackColor = getRandomColor(),
                            uncheckedThumbColor = getRandomColor(),
//                            uncheckedTrackColor = getRandomColor()
                        ),
                        checked = accEnabled,
                        onCheckedChange = {
                            accEnabled = !accEnabled
                        }
                    )
                }
            }

            ClimateLabel(
                text = stringResource(R.string.climate_label_air_source),
                style = ClimateFontStyle.Regular.style,
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.BottomStart
            )

            val airSources = listOf(
                R.string.climate_label_air_source,
                R.string.climate_radio_air_source_recirculate
            )
            val (selectedSource, onOptionSelected) = remember {
                mutableStateOf(airSources[0])
            }
            airSources.forEach{ source ->
                ElevatedCard(
                    onClick = { onOptionSelected(source) },
                    colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(4f),
                ) {
                    ClimateRow(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(cc.padding),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconWithText(
                            text = stringResource(source),
                            textStyle = ClimateFontStyle.Header3.style,
                            imageVector = Icons.Rounded.Refresh,
                            modifier = Modifier.weight(1f)
                        )
                        Checkbox(
                            colors = CheckboxDefaults.colors().copy(
                                checkedCheckmarkColor = Color.Red,
                                checkedBoxColor = Color.Transparent,
                                checkedBorderColor = Color.Transparent,

                                uncheckedBorderColor = Color.Transparent,
                                uncheckedBoxColor = Color.Transparent
                            ),
                            checked = source == selectedSource,
                            onCheckedChange =  { onOptionSelected(source) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x6264,
    device = "spec:width=800px,height=1000px,dpi=480,orientation=portrait",
)
@Composable
fun ACCardPreview() {
    ACCard()
}
