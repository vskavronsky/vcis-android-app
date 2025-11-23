package com.automotive.bootcamp.adas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.adas.R
import com.automotive.bootcamp.adas.domain.model.AdasFeature
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.presentation.ui.AdasCardUi
import com.automotive.bootcamp.adas.presentation.ui.AdasFeatureUiConfig
import com.automotive.bootcamp.adas.presentation.ui.OperationModeUi

@Composable
fun AdasCard(
    config: AdasFeatureUiConfig,
    cardData: AdasCardUi?,
    onFeatureToggle: () -> Unit,
    onSetOperationMode: (mode: AdasFeatureSettings.OperatingMode) -> Unit,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Color(0xfffefffe)),
        shape = RoundedCornerShape(size = 20.dp),
        modifier = Modifier.height(280.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = config.icon,
                    contentDescription = stringResource(id = config.fullTitleId),
                    tint = config.style.highlight,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(id = config.fullTitleId),
                    modifier = Modifier
                        .padding(
                            start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp
                        )
                        .align(Alignment.CenterVertically),
                    style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                    ),
                )
            }
            if (cardData == null) {
                CircularProgressIndicator(
                    color = config.style.highlight,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(64.dp)
                )
            } else {
                Row(
                    modifier = Modifier
                        .height(64.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.adas_system_status),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(fontSize = 16.sp, color = Color.DarkGray),
                    )

                    ToggleButton(
                        style = config.style,
                        text = stringResource(id = if (cardData.active) R.string.adas_state_on else R.string.adas_state_off).uppercase(),
                        selected = cardData.active,
                        onToggle = { onFeatureToggle() })
                }

                Spacer(modifier = Modifier.weight(1.0f))
                OperatingModeButtons(
                    modelData = cardData.mode, style = config.style, onSelect = {
                        onSetOperationMode(it)
                    })
            }
        }
    }
}