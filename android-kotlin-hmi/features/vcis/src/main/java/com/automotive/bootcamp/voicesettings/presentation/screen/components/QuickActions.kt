package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.automotive.bootcamp.voicesettings.R
import com.automotive.bootcamp.voicesettings.theme.BtnPadding
import com.automotive.bootcamp.voicesettings.theme.BtnRadius20
import com.automotive.bootcamp.voicesettings.theme.BtnSpace
import com.automotive.bootcamp.voicesettings.theme.BtnWidth
import com.automotive.bootcamp.voicesettings.theme.ClearHistoryBtnBg
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin16
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin8
import com.automotive.bootcamp.voicesettings.theme.ContainerPadding
import com.automotive.bootcamp.voicesettings.theme.ContainerRadius
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace12
import com.automotive.bootcamp.voicesettings.theme.ResetAllBtnBg
import com.automotive.bootcamp.voicesettings.theme.TestVoiceBtnBg
import com.automotive.bootcamp.voicesettings.theme.TextType
import com.automotive.bootcamp.voicesettings.theme.UpdateCommandsBtnBg
import compose_theme.theme.BackgroundLight

data class QuickActionButton(
    @DrawableRes val iconId: Int,
    val iconDescription: String,
    val text: String,
    val bgColor: Color
)

val buttons = listOf(
    QuickActionButton(
        R.drawable.baseline_mic_24,
        "Mic icon",
        "Test Voice",
        TestVoiceBtnBg
    ),
    QuickActionButton(
        R.drawable.baseline_delete_24,
        "Delete icon",
        "Clear History",
        ClearHistoryBtnBg
    ),
    QuickActionButton(
        R.drawable.baseline_update_24,
        "Update icon",
        "Update Commands",
        UpdateCommandsBtnBg
    ),
    QuickActionButton(
        R.drawable.baseline_restart_alt_24,
        "Restart alt icon",
        "Reset All",
        ResetAllBtnBg
    )
)

@Composable
fun QuickActions(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ContainerSpace12),
        modifier = modifier
            .padding(
                start = ContainerMargin8,
                end = ContainerMargin16,
                top = ContainerMargin8,
                bottom = ContainerMargin16
            )
            .background(color = BackgroundLight, shape = RoundedCornerShape(ContainerRadius))
            .padding(ContainerPadding)
    ) {
        Text(
            text = "Quick Actions",
            style = TextType.headlineSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(ContainerSpace12),
            verticalArrangement = Arrangement.spacedBy(ContainerSpace12)
        ) {
            items(buttons) {
                Row(
                    modifier = Modifier
                        .width(BtnWidth)
                        .clip(RoundedCornerShape(BtnRadius20))
                        .background(it.bgColor)
                        .clickable { }
                        .padding(BtnPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = BtnSpace,
                        alignment = Alignment.CenterHorizontally
                    )
                ) {
                    Icon(
                        painter = painterResource(it.iconId),
                        contentDescription = it.iconDescription,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = it.text,
                        style = TextType.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
