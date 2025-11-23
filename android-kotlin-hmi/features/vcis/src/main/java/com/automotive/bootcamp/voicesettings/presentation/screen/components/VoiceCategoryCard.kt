package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import com.automotive.bootcamp.voicesettings.theme.CardPadding
import com.automotive.bootcamp.voicesettings.theme.CardRadius24
import com.automotive.bootcamp.voicesettings.theme.CardSpace12
import com.automotive.bootcamp.voicesettings.theme.TextType
import com.automotive.bootcamp.voicesettings.utils.categoriesUIData

@Composable
fun VoiceCategoryCard(voiceCategory: VoiceCategory) {
    val uiData = categoriesUIData[voiceCategory.name.lowercase()]
        ?: throw IllegalArgumentException(
            "Using unsupported category: ${voiceCategory.name.lowercase()}"
        )

    Card(
        shape = RoundedCornerShape(CardRadius24),
        colors = CardDefaults.cardColors(
            containerColor = uiData.cardColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = CardSpace12,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier.padding(CardPadding)
        ) {
            IconWithBg(
                iconId = uiData.iconInfo.iconId,
                description = uiData.iconInfo.description,
                padding = uiData.iconInfo.padding,
                cornerRadius = uiData.iconInfo.cornerRadius,
                bgColor = uiData.iconInfo.bgColor
            )
            Text(
                text = voiceCategory.name,
                style = TextType.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = voiceCategory.description,
                style = TextType.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            TextWithBg(
                text = voiceCategory.commandsNumber,
                textColor = uiData.textWithBgInfo.textColor,
                bgColor = uiData.textWithBgInfo.bgColor,
                bgShape = uiData.textWithBgInfo.bgShape,
                padding = uiData.textWithBgInfo.padding
            )
        }
    }
}
