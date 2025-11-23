package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun IconWithBg(
    @DrawableRes iconId: Int,
    description: String,
    padding: Dp,
    cornerRadius: Dp,
    bgColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = bgColor,
                shape = (RoundedCornerShape(cornerRadius))
            )
            .padding(padding)
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = description,
            tint = Color.Unspecified
        )
    }
}
