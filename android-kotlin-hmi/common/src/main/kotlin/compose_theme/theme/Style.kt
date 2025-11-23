package compose_theme.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val primaryTitleStyle = TextStyle(
    fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
)
val secondaryTitleStyle = TextStyle(
    fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
)

val hugeBodyStyle = TextStyle(fontSize = 80.sp, fontWeight = FontWeight.Bold)
val primaryBodyStyle = TextStyle(fontSize = 24.sp)
val primaryBodyBoldStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
val secondaryBodyStyle = TextStyle(fontSize = 18.sp)
val secondaryBodyBoldStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)

val primaryLabelStyle = TextStyle(fontSize = 16.sp)
val secondaryLabelStyle = TextStyle(fontSize = 12.sp)
val secondaryLabelBoldStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)

val primaryBtnTextStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
val secondaryBtnTextStyle = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
