package com.automotive.bootcamp.userprofiles.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationFavorite(
    val destination: String,
    val icon: ImageVector,
    val color: Color,
)

data class ConfigurationInfo(
    var seat: Int,
    var mirrors: Int,
    var climate: Double,
    var media: String,
    val favorites: List<NavigationFavorite>,
)
