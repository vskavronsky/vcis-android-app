import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.userprofiles.domain.model.User
import com.automotive.bootcamp.userprofiles.presentation.state.UserProfilesUIState


@Composable
fun UserCard(
    user: User,
    isCurrent: Boolean,
    onSelect: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.size(width = 200.dp, height = 200.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(width = 86.dp, height = 86.dp)
            ) { }
            Text(
                text = user.name,
                //modifier = Modifier.padding(16.dp),
                fontWeight = if (isCurrent) { FontWeight.Bold } else { FontWeight.Normal },
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Driver${user.id}",
                //modifier = Modifier.padding(16.dp),
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = onSelect,
                colors = ButtonDefaults.buttonColors(
                    containerColor = /*Color.DarkGray*/Color(0xFFBFC6F7), contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(90.dp)
                    .height(32.dp)
            ) {
                Text("Select")
            }
        }
    }
}
