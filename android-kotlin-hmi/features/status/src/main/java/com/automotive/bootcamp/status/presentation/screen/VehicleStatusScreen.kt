package com.automotive.bootcamp.status.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.status.R

@Composable
fun VehicleStatusScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onClose()
    }

    VehicleStatusView(
        modifier = modifier
    )
}

@Composable
fun VehicleStatusView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(16.dp)
    ) {
        // Top bar with icon, text, wifi, signal and time
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_directions_car_24),
                        contentDescription = "Car",
                        tint = Color(0xFF1C3FAA)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Vehicle Status", fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_wifi_24),
                        contentDescription = "Wi-fi",
                        tint = Color(0xFF1C3FAA)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_signal_cellular_4_bar_24),
                        contentDescription = "Signal",
                        tint = Color(0xFF1C3FAA)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("14:30")
                }
            }
        }
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                // Engine Temperature block
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Engine Temperature", fontWeight = FontWeight.SemiBold)
                        Text("87°C", color = Color.Red, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Text("Normal Operating Range", color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Quick Actions block
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        QuickActionButton(
                            text = "Fan Control",
                            bgColor = Color(0xFFDCEEFF),
                            modifier = Modifier.weight(1f)
                        )
                        QuickActionButton(
                            text = "Performance",
                            bgColor = Color(0xFFF3E8FF),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        QuickActionButton(
                            text = "Eco Mode",
                            bgColor = Color(0xFFE5FAF0),
                            modifier = Modifier.weight(1f)
                        )
                        QuickActionButton(
                            text = "Power Boost",
                            bgColor = Color(0xFFFFF2E5),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                // Battery Status block
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Battery Status", fontWeight = FontWeight.SemiBold)
                        Text("85%", color = Color(0xFF4CAF50), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Text("4.2 Hours Remaining", color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // System Notifications block
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    NotificationItem("Tire pressure check recommended", Color(0xFFFFF3CD), Color(0xFFFFC107))
                    NotificationItem("Service due in 2000 km", Color(0xFFE9F1FF), Color(0xFF2196F3))
                    NotificationItem("All systems operating normally", Color(0xFFE6FFF3), Color(0xFF4CAF50))
                }
            }
        }
    }
}

@Composable
fun QuickActionButton(text: String, bgColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(bgColor, shape = RoundedCornerShape(8.dp))
            .clickable { }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun NotificationItem(text: String, bgColor: Color, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(bgColor, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(iconColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 14.sp)
    }
}
