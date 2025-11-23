import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.launcher.R
import com.automotive.bootcamp.launcher.presentation.components.Feature
import compose_theme.theme.ColorMenuButton
import compose_theme.theme.ColorMenuButtonPressed
import compose_theme.theme.ColorMenuButtonSelected

@Composable
fun FeatureButton(
    feature: Feature,
    onClick: () -> Unit = {},
    isSelected: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val color =
        if (isSelected) ColorMenuButtonSelected
        else if (isPressed) ColorMenuButtonPressed
        else ColorMenuButton

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = feature.resId).uppercase(),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.afacad_flux_variable_font_slnt_wght)),
            color = Color.White,
        )
    }
}

@Preview(showSystemUi = true, widthDp = 100)
@Composable
fun PreviewMenuButton() {
    FeatureButton(Feature.EXAMPLE_FEATURE)
}