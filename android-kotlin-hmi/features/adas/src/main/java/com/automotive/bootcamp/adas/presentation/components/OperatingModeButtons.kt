package com.automotive.bootcamp.adas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.adas.domain.model.AdasFeature
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.presentation.ui.ColorStyle
import com.automotive.bootcamp.adas.presentation.ui.OperationModeUi

@Composable
fun OperatingModeButtons(
    modelData: OperationModeUi,
    style: ColorStyle,
    onSelect: (mode: AdasFeatureSettings.OperatingMode) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = stringResource(id = modelData.titleId),
            style = TextStyle(fontSize = 16.sp, color = Color.DarkGray),
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )

        if (modelData.strings.size == 3) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (entry in modelData.strings.entries.iterator()) {
                    ToggleButton(
                        style = style,
                        text = stringResource(id = entry.value),
                        selected = entry.key == modelData.mode,
                        onToggle = { onSelect(entry.key) },
                        modifier = Modifier.weight(1.0f)
                    )
                }

            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (entry in modelData.strings.entries.iterator()) {
                    ToggleButton(
                        style = style,
                        text = stringResource(id = entry.value),
                        textCentered = false,
                        selected = entry.key == modelData.mode,
                        onToggle = { onSelect(entry.key) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }
    }
}