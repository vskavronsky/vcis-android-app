package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.automotive.bootcamp.voicesettings.theme.TextType

@Composable
fun TextWithBg(
    text: String,
    textColor: Color,
    bgColor: Color,
    bgShape: Shape,
    padding: Dp
) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                color = bgColor,
                shape = bgShape
            )
            .padding(padding),
        style = TextType.titleSmall,
        fontWeight = FontWeight.Bold,
        color = textColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
