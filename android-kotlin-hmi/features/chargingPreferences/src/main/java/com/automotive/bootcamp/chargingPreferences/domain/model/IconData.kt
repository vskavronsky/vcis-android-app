package com.automotive.bootcamp.chargingPreferences.domain.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class IconData (val description: String, val painter: Painter? = null, val vector: ImageVector? = null)