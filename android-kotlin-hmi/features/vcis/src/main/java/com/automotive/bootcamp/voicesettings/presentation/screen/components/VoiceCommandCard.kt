package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.automotive.bootcamp.voicesettings.R
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.theme.BtnRadius8
import com.automotive.bootcamp.voicesettings.theme.BtnSize
import com.automotive.bootcamp.voicesettings.theme.CardPadding
import com.automotive.bootcamp.voicesettings.theme.CardRadius20
import com.automotive.bootcamp.voicesettings.theme.CardSpace12
import com.automotive.bootcamp.voicesettings.theme.CardSpace8
import com.automotive.bootcamp.voicesettings.theme.DropdownMenuBgColor
import com.automotive.bootcamp.voicesettings.theme.DropdownMenuRadius
import com.automotive.bootcamp.voicesettings.theme.IconDeleteColor
import com.automotive.bootcamp.voicesettings.theme.IconSize
import com.automotive.bootcamp.voicesettings.theme.IconUpdateColor
import com.automotive.bootcamp.voicesettings.theme.ItemDeleteBgColor
import com.automotive.bootcamp.voicesettings.theme.ItemDeleteColor
import com.automotive.bootcamp.voicesettings.theme.ItemUpdateBgColor
import com.automotive.bootcamp.voicesettings.theme.ItemUpdateColor
import com.automotive.bootcamp.voicesettings.theme.MoreVertBtnBgColor
import com.automotive.bootcamp.voicesettings.theme.TextType
import com.automotive.bootcamp.voicesettings.utils.commandsUIData

@Composable
fun VoiceCommandCard(voiceCommand: VoiceCommand, onDeleteCommand: (VoiceCommand) -> Unit) {
    var showPopupMenu by remember { mutableStateOf(false) }
    val uiData = commandsUIData[voiceCommand.categoryName.lowercase()]
        ?: throw IllegalArgumentException(
            "Using unsupported category: ${voiceCommand.categoryName.lowercase()}" +
                    "for command: ${voiceCommand.command}"
        )

    Card(
        shape = RoundedCornerShape(CardRadius20),
        colors = CardDefaults.cardColors(
            containerColor = uiData.cardColor
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(CardPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = CardSpace12,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconWithBg(
                    iconId = uiData.iconInfo.iconId,
                    description = uiData.iconInfo.description,
                    padding = uiData.iconInfo.padding,
                    cornerRadius = uiData.iconInfo.cornerRadius,
                    bgColor = uiData.iconInfo.bgColor
                )
                Column {
                    Text(
                        text = voiceCommand.command,
                        style = TextType.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = voiceCommand.description,
                        style = TextType.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(
                    space = CardSpace8,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithBg(
                    text = voiceCommand.categoryName,
                    textColor = uiData.textWithBgInfo.textColor,
                    bgColor = uiData.textWithBgInfo.bgColor,
                    bgShape = uiData.textWithBgInfo.bgShape,
                    padding = uiData.textWithBgInfo.padding
                )
                Box {
                    IconButton(
                        onClick = { showPopupMenu = true },
                        modifier = Modifier
                            .background(
                                color = MoreVertBtnBgColor,
                                shape = RoundedCornerShape(BtnRadius8)
                            )
                            .size(BtnSize),
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.DarkGray)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "MoreVert icon "
                        )
                    }

                    DropdownMenu(
                        shape = RoundedCornerShape(DropdownMenuRadius),
                        containerColor = DropdownMenuBgColor,
                        expanded = showPopupMenu,
                        onDismissRequest = { showPopupMenu = false }
                    ) {
                        DropdownMenuItem(
                            modifier = Modifier.background(ItemUpdateBgColor),
                            text = {
                                Text(
                                    text = "Update", style = TextType.titleMedium, maxLines = 1,
                                    overflow = TextOverflow.Ellipsis, color = ItemUpdateColor
                                )
                            },
                            onClick = { showPopupMenu = false },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_update_24),
                                    contentDescription = "Update icon",
                                    tint = IconUpdateColor,
                                    modifier = Modifier
                                        .size(IconSize)
                                )
                            }
                        )
                        DropdownMenuItem(
                            modifier = Modifier.background(ItemDeleteBgColor),
                            text = {
                                Text(
                                    text = "Delete", style = TextType.titleMedium, maxLines = 1,
                                    overflow = TextOverflow.Ellipsis, color = ItemDeleteColor
                                )
                            },
                            onClick = {
                                onDeleteCommand(voiceCommand)
                                showPopupMenu = false
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_delete_24),
                                    contentDescription = "Delete icon",
                                    tint = IconDeleteColor,
                                    modifier = Modifier
                                        .size(IconSize)
                                )
                            }
                        )
                        HorizontalDivider(color = Color.Black)
                    }
                }
            }
        }
    }
}
