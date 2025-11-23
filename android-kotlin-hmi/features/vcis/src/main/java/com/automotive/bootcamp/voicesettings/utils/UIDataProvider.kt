package com.automotive.bootcamp.voicesettings.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.automotive.bootcamp.voicesettings.R
import com.automotive.bootcamp.voicesettings.theme.ClimateCommandCardColor
import com.automotive.bootcamp.voicesettings.theme.IconClimateBg
import com.automotive.bootcamp.voicesettings.theme.IconClimateColor
import com.automotive.bootcamp.voicesettings.theme.IconMediaBg
import com.automotive.bootcamp.voicesettings.theme.IconMediaColor
import com.automotive.bootcamp.voicesettings.theme.IconNavBg
import com.automotive.bootcamp.voicesettings.theme.IconNavColor
import com.automotive.bootcamp.voicesettings.theme.IconPadding10
import com.automotive.bootcamp.voicesettings.theme.IconPadding12
import com.automotive.bootcamp.voicesettings.theme.IconPhoneBg
import com.automotive.bootcamp.voicesettings.theme.IconPhoneColor
import com.automotive.bootcamp.voicesettings.theme.IconRadius14
import com.automotive.bootcamp.voicesettings.theme.IconRadius16
import com.automotive.bootcamp.voicesettings.theme.IconVehicleBg
import com.automotive.bootcamp.voicesettings.theme.IconVehicleColor
import com.automotive.bootcamp.voicesettings.theme.MediaCommandCardColor
import com.automotive.bootcamp.voicesettings.theme.NavCommandCardColor
import compose_theme.theme.BackgroundLight

sealed class UIDataInfo(
    val iconInfo: IconInfo,
    val textWithBgInfo: TextWithBgInfo,
    val cardColor: Color
) {
    data class IconInfo(
        @DrawableRes val iconId: Int,
        val description: String,
        val bgColor: Color,
        val padding: Dp,
        val cornerRadius: Dp
    )

    data class TextWithBgInfo(
        val textColor: Color,
        val bgColor: Color,
        val bgShape: Shape = CircleShape,
        val padding: Dp = 8.dp
    )

    // Categories
    data object Navigation : UIDataInfo(
        IconInfo(
            R.drawable.baseline_route_32,
            "Route icon",
            IconNavBg,
            IconPadding12,
            IconRadius16
        ),
        TextWithBgInfo(IconNavColor, IconNavBg), BackgroundLight
    )

    data object Media : UIDataInfo(
        IconInfo(
            R.drawable.baseline_music_note_32,
            "Music note icon",
            IconMediaBg,
            IconPadding12,
            IconRadius16
        ),
        TextWithBgInfo(IconMediaColor, IconMediaBg), BackgroundLight
    )

    data object Climate : UIDataInfo(
        IconInfo(
            R.drawable.baseline_thermostat_32,
            "Thermostat icon",
            IconClimateBg,
            IconPadding12,
            IconRadius16
        ),
        TextWithBgInfo(IconClimateColor, IconClimateBg), BackgroundLight
    )

    data object Phone : UIDataInfo(
        IconInfo(
            R.drawable.baseline_call_32,
            "Call icon",
            IconPhoneBg,
            IconPadding12,
            IconRadius16
        ),
        TextWithBgInfo(IconPhoneColor, IconPhoneBg), BackgroundLight
    )

    data object Vehicle : UIDataInfo(
        IconInfo(
            R.drawable.baseline_directions_car_32,
            "Car icon",
            IconVehicleBg,
            IconPadding12,
            IconRadius16
        ),
        TextWithBgInfo(IconVehicleColor, IconVehicleBg), BackgroundLight
    )

    // Commands
    data object Navigate : UIDataInfo(
        IconInfo(
            R.drawable.baseline_location_on_24,
            "Location on icon",
            IconNavBg,
            IconPadding10,
            IconRadius14
        ),
        TextWithBgInfo(IconNavColor, IconNavBg), NavCommandCardColor
    )

    data object Play : UIDataInfo(
        IconInfo(
            R.drawable.baseline_play_arrow_24,
            "Play arrow icon",
            IconMediaBg,
            IconPadding10,
            IconRadius14
        ),
        TextWithBgInfo(IconMediaColor, IconMediaBg), MediaCommandCardColor
    )

    data object SetTemperature : UIDataInfo(
        IconInfo(
            R.drawable.baseline_ac_unit_24,
            "AC unit icon",
            IconClimateBg,
            IconPadding10,
            IconRadius14
        ),
        TextWithBgInfo(IconClimateColor, IconClimateBg), ClimateCommandCardColor
    )
}

val categoriesUIData = buildMap {
    put("navigation", UIDataInfo.Navigation)
    put("media", UIDataInfo.Media)
    put("climate", UIDataInfo.Climate)
    put("phone", UIDataInfo.Phone)
    put("vehicle", UIDataInfo.Vehicle)
}

val commandsUIData = buildMap {
    put("navigation", UIDataInfo.Navigate)
    put("media", UIDataInfo.Play)
    put("climate", UIDataInfo.SetTemperature)
}
