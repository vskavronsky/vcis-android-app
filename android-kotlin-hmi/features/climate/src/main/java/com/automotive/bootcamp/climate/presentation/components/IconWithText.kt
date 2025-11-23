package com.automotive.bootcamp.climate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.automotive.bootcamp.climate.presentation.ClimateFontStyle

@Composable
fun IconWithText(
    text: String? = null,
    textStyle: TextStyle = ClimateFontStyle.Regular.style,
    colors: CardColors = CardDefaults.cardColors(containerColor = getRandomColor()),
    imageVector: ImageVector? = null,
    modifier: Modifier = Modifier,
    iconTint: Color = colors.contentColor,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
): Unit {
    val estimatedSpacing = (textStyle.fontSize.value / 3).dp

    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.spacedBy(estimatedSpacing)
    ) {
        imageVector?.also {
            Icon(
                imageVector = imageVector,
                tint = iconTint,
                contentDescription = text?: "icon",
            )
        }
        text?.also {
            Text(
                text = text,
                style = textStyle
            )
        }
    }

}