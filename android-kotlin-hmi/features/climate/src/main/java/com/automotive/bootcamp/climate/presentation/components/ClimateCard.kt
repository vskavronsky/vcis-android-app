package com.automotive.bootcamp.climate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.automotive.bootcamp.climate.presentation.*
import kotlin.random.Random

object hueRoller {
    private var counter = Random.nextInt(360)
    fun next(): Float {
        counter = (counter + 101) % 360
        return counter.toFloat()
    }
}

fun getRandomColor() = Color.hsv(
    hue = hueRoller.next(),
    saturation = .13f,
    value = 1f,
)

@Composable
fun ClimateCard(
    colors: CardColors = CardDefaults.cardColors(containerColor = getRandomColor()),
    title: String? = null,
    titleStyle: ClimateFontStyle = ClimateFontStyle.Regular,
    icon: ImageVector? = null,
    iconTint: Color = colors.contentColor,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    /*Elevated*/Card(
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(size = cc.padding),
//        enabled = enabled
    ) {
        val estimatedSpacing = (titleStyle.style.fontSize.value / 3).dp
        Column(
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = cc.padding,
                start = cc.padding,
                end = cc.padding
            )
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(estimatedSpacing)
            ) {
                icon?.also {
                    Icon(
                        imageVector = icon,
                        tint = iconTint,
                        contentDescription = title,
                    )
                }
                title?.also {
                    Text(
                        text = title,
                        style = titleStyle.style
                    )
                }
            }

            content()
        }

    }
}