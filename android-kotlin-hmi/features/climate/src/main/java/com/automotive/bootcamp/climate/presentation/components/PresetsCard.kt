package com.automotive.bootcamp.climate.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.climate.R
import com.automotive.bootcamp.climate.presentation.*

@Composable
fun PresetsCard(
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
                text = stringResource(R.string.climate_title_presets_card),
                textStyle = ClimateFontStyle.Header2.style,
                imageVector = Icons.Rounded.AccountBox
            )

            data class ClimatePreset(
                val titleR: Int,
                val descriptionR: Int,
                val icon: ImageVector,
                val iconTint: Color,
                val action: () -> Unit
            )

            val modes = listOf(
                listOf(
                    ClimatePreset(
                        R.string.climate_presets_title_cool_mode,
                        R.string.climate_preset_cool,
                        Icons.Rounded.Settings,
                        Color.Blue.copy(alpha = 0.6f),
                        {}
                    ),
                    ClimatePreset(
                        R.string.climate_presets_title_warm_mode,
                        R.string.climate_preset_warm,
                        Icons.Rounded.AddCircle,
                        Color.Red.copy(alpha = 0.6f),
                        {}
                    )
                ), listOf(
                    ClimatePreset(
                        R.string.climate_presets_title_eco_mode,
                        R.string.climate_preset_eco,
                        Icons.Rounded.FavoriteBorder,
                        Color.Green.copy(alpha = 0.6f),
                        {}
                    ),
                    ClimatePreset(
                        R.string.climate_presets_title_auto_mode,
                        R.string.climate_preset_auto,
                        Icons.Rounded.Create,
                        Color.Magenta.copy(alpha = 0.6f),
                        {}
                    )
                )
            )

            modes.forEach {
                Row(
                    modifier = Modifier
                        .weight(15f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.spacedBy(cc.padding)
                ) {
                    it.forEach {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(cc.padding)
                        ) {
                            ElevatedCard(
                                onClick = it.action,
                                enabled = true,
                                colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                            ) {
                                ClimateColumn(
                                    modifier = Modifier.padding(cc.padding)
                                ) {
                                    IconWithText(
                                        imageVector = it.icon,
                                        iconTint = it.iconTint,
                                        text = stringResource(it.titleR),
                                        textStyle = ClimateFontStyle.Header3.style,
                                        modifier = Modifier.weight(1f),
                                        verticalAlignment = Alignment.Bottom
                                    )
                                    Text(
                                        text = stringResource(it.descriptionR),
                                        style = ClimateFontStyle.Regular.style,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Text(
//                modifier = Modifier.weight,
                text = stringResource(R.string.climate_title_quick_actions),
                style = ClimateFontStyle.Header3.style
            )

            val quickActions = listOf(
                ClimatePreset(
                    R.string.climate_preset_action_defrost,
                    0,
                    Icons.Rounded.Menu,
                    Color.Black,
                    {}
                ),
                ClimatePreset(
                    R.string.climate_preset_action_maxac,
                    0,
                    Icons.Rounded.ShoppingCart,
                    Color.Black,
                    {}
                ),
                ClimatePreset(
                    R.string.climate_preset_action_off,
                    0,
                    Icons.Rounded.Lock,
                    Color.Black,
                    {}
                ))
            ClimateRow(
                modifier = Modifier.weight(12f)
            ) {
                quickActions.forEach {
                    ElevatedCard(
                        onClick = it.action,
                        enabled = true,
                        colors = CardDefaults.cardColors(containerColor = getRandomColor()),
                        modifier = Modifier.weight(1f),
                    ) {
                        Column(
                            modifier = Modifier.padding(cc.padding).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val text = stringResource(it.titleR)
                            Icon(
                                modifier = Modifier.weight(1f),
                                imageVector = it.icon,
                                contentDescription = text,
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = stringResource(it.titleR),
                                style = ClimateFontStyle.Regular.style,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x6264,
    device = "spec:width=1080px,height=1200px,dpi=480,orientation=landscape",
)
@Composable
fun PresetsCardPreview() {
    PresetsCard()
}
