package com.automotive.bootcamp.climate.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.text.style.TextAlign
import com.automotive.bootcamp.climate.presentation.*
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.climate.R
import kotlin.math.roundToInt
import kotlin.reflect.KProperty

@Composable
fun FanCard(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = getRandomColor()),
        modifier = modifier.fillMaxSize()
    ) {
        ClimateColumn(
            modifier = Modifier.padding(cc.padding)
        ) {
            var fanSpeed by remember { mutableStateOf(3f) }
            IconWithText(
                text = stringResource(R.string.climate_title_fan_card),
                textStyle = ClimateFontStyle.Header2.style,
                imageVector = Icons.Rounded.Clear
            )
            Text(
                modifier = Modifier.weight(0.2f),
                text = stringResource(R.string.climate_label_fan_speed),
                style = ClimateFontStyle.Regular.style
            )

            Row(
                modifier = Modifier.weight(0.5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "lower"
                )

                Slider(
                    valueRange = 0f..5f,
                    steps = 6,
                    value = fanSpeed,
                    onValueChange = { fanSpeed = it },
                    modifier = Modifier.weight(6f),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                )

                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = "higher"
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                text = stringResource(
                    R.string.climate_label_fan_level,
                    fanSpeed.roundToInt()
                ),
                style = ClimateFontStyle.Header3.style,
                textAlign = TextAlign.Center,
                color = Color.Green
            )
            ClimateLabel(
                modifier = Modifier.weight(0.2f),
                text = stringResource(R.string.climate_label_airflow_dir),
                contentAlignment = Alignment.BottomStart
            )

            ClimateRow(
                modifier = Modifier.weight(1f),
            ) {
                data class AirFlow(
                    val textR: Int,
                    val imageVector: ImageVector,
                    val action: () -> Unit
                )

                val airDirections = listOf(
                    AirFlow(
                        R.string.climate_button_face_dir,
                        Icons.Rounded.KeyboardArrowUp,
                        {}
                    ),
                    AirFlow(
                        R.string.climate_button_mixed_dir,
                        Icons.Rounded.Menu,
                        {}
                    ),
                    AirFlow(
                        R.string.climate_button_feet_dir,
                        Icons.Rounded.KeyboardArrowDown,
                        {}
                    ),
                )
                val (selectedSource, onOptionSelected) = remember {
                    mutableStateOf(airDirections[0])
                }
                airDirections.forEach {
                    ClimateButton(
                        onClick = it.action,
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        topComponent = {
                            Icon(
                                imageVector = it.imageVector,
                                contentDescription = stringResource(it.textR)
                            )
                        },
                        bottomComponent = {
                            Text(
                                text = stringResource(it.textR),
                                style = ClimateFontStyle.Regular.style
                            )
                        }
                    )
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
fun FanCardPreview() {
    FanCard()
}
