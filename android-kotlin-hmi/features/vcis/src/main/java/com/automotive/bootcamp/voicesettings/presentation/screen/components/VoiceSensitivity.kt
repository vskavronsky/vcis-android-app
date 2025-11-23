package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import com.automotive.bootcamp.voicesettings.R
import com.automotive.bootcamp.voicesettings.theme.ActiveTrackColor
import com.automotive.bootcamp.voicesettings.theme.AddBtnBgColor
import com.automotive.bootcamp.voicesettings.theme.BtnRadius8
import com.automotive.bootcamp.voicesettings.theme.BtnSize
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin16
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin8
import com.automotive.bootcamp.voicesettings.theme.ContainerPadding
import com.automotive.bootcamp.voicesettings.theme.ContainerRadius
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace12
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace8
import com.automotive.bootcamp.voicesettings.theme.InactiveTickColor
import com.automotive.bootcamp.voicesettings.theme.RemoveBtnBgColor
import com.automotive.bootcamp.voicesettings.theme.SliderWidth
import com.automotive.bootcamp.voicesettings.theme.SwitchCheckedColor
import com.automotive.bootcamp.voicesettings.theme.SwitchHeight
import com.automotive.bootcamp.voicesettings.theme.SwitchUncheckedColor
import com.automotive.bootcamp.voicesettings.theme.TextType
import com.automotive.bootcamp.voicesettings.theme.ThumbColor
import com.automotive.bootcamp.voicesettings.theme.ThumbHeight
import com.automotive.bootcamp.voicesettings.theme.ThumbTrackGapSize
import com.automotive.bootcamp.voicesettings.theme.ThumbWidth
import compose_theme.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceSensitivity(modifier: Modifier) {
    var checked by remember { mutableStateOf(false) }

    val sliderState = remember {
        SliderState(
            steps = 9,
            valueRange = 0f..10f
        )
    }
    val interactionSource = remember { MutableInteractionSource() }
    val colors =
        SliderDefaults.colors(
            thumbColor = ThumbColor,
            activeTrackColor = ActiveTrackColor,
            inactiveTickColor = InactiveTickColor
        )

    Column(
        verticalArrangement = Arrangement.spacedBy(ContainerSpace12),
        modifier = modifier
            .padding(
                start = ContainerMargin16,
                end = ContainerMargin8,
                top = ContainerMargin8,
                bottom = ContainerMargin16
            )
            .background(color = BackgroundLight, shape = RoundedCornerShape(ContainerRadius))
            .padding(ContainerPadding)
    ) {
        Text(
            text = "Voice Sensitivity",
            style = TextType.headlineSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Activation Sensitivity",
                style = TextType.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = ContainerSpace8,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                IconButton(
                    onClick = { sliderState.value-- },
                    modifier = Modifier
                        .background(
                            color = RemoveBtnBgColor,
                            shape = RoundedCornerShape(BtnRadius8)
                        )
                        .size(BtnSize)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_24),
                        contentDescription = "Remove icon",
                        tint = Color.Unspecified
                    )
                }
                Slider(
                    state = sliderState,
                    modifier = Modifier
                        .width(SliderWidth)
                        .semantics {
                            contentDescription = "Localized Description"
                        },
                    interactionSource = interactionSource,
                    thumb = {
                        SliderDefaults.Thumb(
                            interactionSource = interactionSource,
                            colors = colors,
                            thumbSize = DpSize(ThumbWidth, ThumbHeight)
                        )
                    },
                    track = {
                        SliderDefaults.Track(
                            colors = colors,
                            sliderState = sliderState,
                            thumbTrackGapSize = ThumbTrackGapSize
                        )
                    }
                )
                IconButton(
                    onClick = { sliderState.value++ },
                    modifier = Modifier
                        .background(
                            color = AddBtnBgColor,
                            shape = RoundedCornerShape(BtnRadius8)
                        )
                        .size(BtnSize)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = "Add icon",
                        tint = Color.Unspecified
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Noise Cancellation",
                style = TextType.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Switch(
                modifier = Modifier.height(SwitchHeight),
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = SwitchCheckedColor,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = SwitchUncheckedColor,
                )
            )
        }
    }
}
