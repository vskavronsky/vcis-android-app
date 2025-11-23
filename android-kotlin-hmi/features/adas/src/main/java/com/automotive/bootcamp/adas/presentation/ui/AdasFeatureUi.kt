package com.automotive.bootcamp.adas.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.automotive.bootcamp.adas.R
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType

sealed class AdasFeatureUiConfig(
    val icon: ImageVector,
    val titleId: Int,
    val fullTitleId: Int = titleId,
    val style: ColorStyle,
) {
    data object AdaptiveCruise : AdasFeatureUiConfig(
        icon = Icons.Rounded.AddCircle,
        titleId = R.string.adas_acc_title,
        fullTitleId = R.string.adas_acc_full_title,
        style = ColorStyle.Blue,
    )

    data object LaneKeeping : AdasFeatureUiConfig(
        icon = Icons.Rounded.Menu,
        titleId = R.string.adas_lane_assist_title,
        fullTitleId = R.string.adas_lane_assist_full_title,
        style = ColorStyle.Green,
    )

    data object BlindSpot : AdasFeatureUiConfig(
        icon = Icons.Rounded.Face,
        titleId = R.string.adas_blind_spot_title,
        fullTitleId = R.string.adas_blind_spot_full_title,
        style = ColorStyle.Orange,

    )

    data object CollisionWarning : AdasFeatureUiConfig(
        icon = Icons.Rounded.Warning,
        titleId = R.string.adas_collision_warning_title,
        fullTitleId = R.string.adas_collision_warning_full_title,
        style = ColorStyle.Purple,
    )
}

fun AdasFeatureType.getUiConfig(): AdasFeatureUiConfig {
    return when (this) {
        AdasFeatureType.ADAPTIVE_CRUISE_CONTROL -> AdasFeatureUiConfig.AdaptiveCruise
        AdasFeatureType.LANE_KEEPING_ASSIST -> AdasFeatureUiConfig.LaneKeeping
        AdasFeatureType.BLIND_SPOT_MONITORING -> AdasFeatureUiConfig.BlindSpot
        AdasFeatureType.FORWARD_COLLISION_WARNING -> AdasFeatureUiConfig.CollisionWarning
    }
}

@Immutable
sealed class ColorStyle(
    val background: Color,
    val highlight: Color,
    val text: Color,
    val textBackground: Color
) {
    data object Blue : ColorStyle(
        background = Color(0xffe8f4fd),
        highlight = Color(0xff3981f6),
        text = Color(0xff466ddf),
        textBackground = Color(0xffb8e0fe)
    )

    data object Green : ColorStyle(
        background = Color(0xffe8f6e8),
        highlight = Color(0xff20c45d),
        text = Color(0xff448653),
        textBackground = Color(0xffb7f0b7)
    )

    data object Orange : ColorStyle(
        background = Color(0xfffff4e9),
        highlight = Color(0xffee6e0f),
        text = Color(0xffbf6640),
        textBackground = Color(0xffffd3b8)
    )

    data object Purple : ColorStyle(
        background = Color(0xfff3e8fe),
        highlight = Color(0xffa755f7),
        text = Color(0xff795a9d),
        textBackground = Color(0xffd3b7fe)
    )
}
