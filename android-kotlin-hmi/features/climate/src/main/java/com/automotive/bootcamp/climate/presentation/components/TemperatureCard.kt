package com.automotive.bootcamp.climate.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.automotive.bootcamp.climate.presentation.*
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.climate.R

@Composable
fun TemperatureCard(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = getRandomColor()),
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(cc.padding)
        ) {
            var targetTemperature by remember { mutableStateOf(22) }

            IconWithText(
                text = stringResource(R.string.climate_title_temperature_card),
                textStyle = ClimateFontStyle.Header2.style,
                imageVector = Icons.Rounded.LocationOn
            )

            ClimateLabel(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(
                    R.string.climate_label_target_temperature,
                    targetTemperature
                ),
                style = ClimateFontStyle.Huge.style,
                color = Color.Blue
            )

            ClimateRow(
                modifier = Modifier
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { targetTemperature -= 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xfffefffe), contentColor = Color.Black
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        style = ClimateFontStyle.Header2.style
                    )
                }
                Button(
                    onClick = { targetTemperature += 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xfffefffe), contentColor = Color.Black
                    ),
                    shape = CircleShape,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    Text(
                        text = "+",
                        textAlign = TextAlign.Center,
                        style = ClimateFontStyle.Header2.style
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            ClimateRow(
                modifier = Modifier.weight(1f)
            ) {
                data class TemperatureMode(
                    val targetTemperature: Int,
                    val descriptionR: Int,
                    val action: () -> Unit
                )

                val temperatureModes = listOf(
                    TemperatureMode(
                        18,
                        R.string.climate_button_cool,
                        { targetTemperature = 18 }
                    ),
                    TemperatureMode(
                        24,
                        R.string.climate_button_comfort,
                        { targetTemperature = 24 }
                    ),
                )
                temperatureModes.forEach {
                    ClimateButton(
                        modifier = Modifier.weight(1f),
                        onClick = it.action,
                        topComponent = {
                            ClimateLabel(
                                modifier = Modifier.weight(1f),
                                text = stringResource(
                                    R.string.climate_label_target_temperature,
                                    it.targetTemperature
                                ),
                                style = ClimateFontStyle.Header2.style,
                                contentAlignment = Alignment.BottomCenter
                            )
                        },
                        bottomComponent = {
                            ClimateLabel(
                                modifier = Modifier.weight(1f),
                                text = stringResource(it.descriptionR),
                                style = ClimateFontStyle.Regular.style,
                                contentAlignment = Alignment.TopCenter
                            )
                        }
                    )
//                    Button(
//                        onClick = it.action,
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xfffefffe),
//                            contentColor = Color.Black
//                        ),
//                        shape = RoundedCornerShape(cc.padding),
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Column() {
//                            ClimateLabel(
//                                modifier = Modifier.weight(1f),
//                                text = stringResource(
//                                    R.string.climate_label_target_temperature,
//                                    it.targetTemperature
//                                ),
//                                style = ClimateFontStyle.Header2.style,
//                                contentAlignment = Alignment.BottomCenter
//                            )
//                            ClimateLabel(
//                                modifier = Modifier.weight(1f),
//                                text = stringResource(it.descriptionR),
//                                style = ClimateFontStyle.Regular.style,
//                                contentAlignment = Alignment.TopCenter
//                            )
//                        }
//                    }
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
fun TemperatureCardPreview() {
    TemperatureCard()
}
