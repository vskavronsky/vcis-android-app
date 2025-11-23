package com.automotive.bootcamp.climate.presentation

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object cc {
    val padding = 10.dp
}

enum class ClimateFontStyle(val style: TextStyle) {
    Huge(
        TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    Header1(
        TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    Header2(
        TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    Header3(
        TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    Regular(
        TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal
        )
    ),
    Thin(
        TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Thin
        )
    ),

}
