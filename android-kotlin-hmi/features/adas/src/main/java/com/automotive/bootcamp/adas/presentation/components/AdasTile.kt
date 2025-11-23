package com.automotive.bootcamp.adas.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.adas.presentation.ui.AdasFeatureUiConfig
import com.automotive.bootcamp.adas.presentation.ui.AdasTileUi
import com.automotive.bootcamp.adas.presentation.ui.ColorStyle

@Composable
fun AdasTile(
    config: AdasFeatureUiConfig,
    tileData: AdasTileUi?
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = config.style.background),
        shape = RoundedCornerShape(size = 20.dp),
        modifier = Modifier
            .width(275.dp)
            .height(128.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = config.icon,
                    contentDescription = stringResource(id=config.titleId),
                    tint = config.style.highlight,
                    modifier = Modifier.size(30.dp)
                )
                if (tileData == null) {
                    CircularProgressIndicator(
                        color = config.style.highlight,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(24.dp)
                    )
                } else {
                    Text(
                        text = stringResource(id=tileData.statusTextId),
                        modifier = Modifier
                            .background(
                                config.style.textBackground,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                            .align(Alignment.CenterVertically),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = config.style.text,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = stringResource(id=config.titleId),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 3.dp, bottom = 3.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            )
            tileData?.let {
                Text(
                    text = tileData.stateInfoText,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 3.dp),
                    style = TextStyle(fontSize = 14.sp, color = Color.DarkGray),
                )
            }
        }
    }
}