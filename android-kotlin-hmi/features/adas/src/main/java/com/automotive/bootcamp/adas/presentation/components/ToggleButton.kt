package com.automotive.bootcamp.adas.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.adas.presentation.ui.ColorStyle

@Composable
fun ToggleButton(
    style: ColorStyle,
    text: String,
    textCentered: Boolean = true,
    selected: Boolean = false,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) style.highlight else style.background,
            contentColor = if (selected) Color.White else style.text
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = { onToggle() },
        modifier = Modifier.then(modifier)
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
        )
        if (!textCentered) {
            Spacer(Modifier.weight(1.0f))
        }
    }
}