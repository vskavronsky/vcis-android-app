package com.automotive.bootcamp.climate.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.automotive.bootcamp.climate.R
import com.automotive.bootcamp.climate.presentation.ClimateFontStyle
import com.automotive.bootcamp.climate.presentation.cc

@Composable
fun ClimateColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(cc.padding),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
//            .padding(cc.padding),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}

@Composable
fun ClimateRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(cc.padding),
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxHeight(),
//            .padding(cc.padding),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) { content() }
}

@Composable
fun ClimateLabel(
    text: String,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    contentAlignment: Alignment = Alignment.Center,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = text,
            color = color,
            style = style
        )
    }
}

@Composable
fun ClimateButton(
    onClick: () -> Unit,
    topComponent: @Composable () -> Unit,
    bottomComponent: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xfffefffe),
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(cc.padding),
        modifier = modifier,
        contentPadding = PaddingValues(cc.padding)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topComponent()
            bottomComponent?.invoke()
        }
    }
}