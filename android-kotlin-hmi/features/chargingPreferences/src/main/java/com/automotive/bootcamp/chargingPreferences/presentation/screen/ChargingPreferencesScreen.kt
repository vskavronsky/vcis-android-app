package com.automotive.bootcamp.chargingPreferences.presentation.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.automotive.bootcamp.chargingPreferences.R.*
import com.automotive.bootcamp.chargingPreferences.domain.model.IconData
import com.automotive.bootcamp.chargingPreferences.domain.model.ScheduleData
import com.automotive.bootcamp.chargingPreferences.domain.model.ScheduleValues
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun ChargingPreferencesScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    BackHandler {
        onClose()
    }

    ChargingPreferencesView(
        modifier = modifier,
    )
}

@Composable
fun ChargingPreferencesView(
    modifier: Modifier = Modifier
) {
    val progress = 0.75f
    val faceRed = Color(0xFFFF808C)
    val faceGreen = Color(0xFF8CFFAF)
    val faceBlue = Color(0xFF8CAFFF)
    val faceGray = Color(0xFF8C8C8C)
    val backRed = Color(0xFFFBE6E8)
    val backGreen = Color(0xFFE8FBEA)
    val backBlue = Color(0xFFE9EBFB)
    val backGray = Color(0xFFE8E8E8)
    val paleBlue = Color(0x6FF0F2FF)

    var schedules = listOf(
        ScheduleData(
            day = ScheduleValues.weekDay(),
            startTime = LocalTime.of(23, 0),
            endTime = LocalTime.of(7, 0),
            enabled = true
            ),
        ScheduleData(
            day = ScheduleValues.weekEnd(),
            startTime = LocalTime.of(22, 0),
            endTime = LocalTime.of(8, 0),
            enabled = true
        )
    )
    val scheduleUpdate = mutableListOf<ScheduleData>()
    fun shortDay(day : DayOfWeek) : CharSequence {
        return  day.toString().substring(0, 3)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(10.dp)
    ) {
        // Central bar with all content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = drawable.ev_ch_station),
                    contentDescription = "Charger",
                    tint = faceBlue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Charging Preferences", fontWeight = FontWeight.Bold, fontSize = 28.sp)
                Spacer(modifier = Modifier.width(8.dp))
                SimpleButton(text = "Settings", bgColor = backBlue, fgColor = faceBlue,
                    icon = IconData(painter = painterResource(id = drawable.settings), description = "Settings")
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            Column (
                modifier = Modifier
                    .padding(10.dp)
                    .background(backBlue, shape = RoundedCornerShape(12.dp)),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row (
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Current Battery Status",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Connected to home charger",
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            "${"%.0f".format(progress * 100)}%",
                            fontWeight = FontWeight.Bold,
                            fontSize = 60.sp,
                            color = faceBlue
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround){
                    LinearProgressIndicator(progress={progress}, color = faceBlue, modifier = Modifier.height(14.dp).fillMaxWidth().padding(horizontal = 0.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Target Charging Level",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier=Modifier.fillMaxWidth().padding(0.dp)
                ) {
                    Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                        SimpleButton(text = "80%", bgColor = backRed, fgColor = faceRed,modifier=Modifier.fillMaxWidth())
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                        SimpleButton(text = "90%", bgColor = backBlue, fgColor = faceBlue,modifier=Modifier.fillMaxWidth())
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                        SimpleButton(text = "100%", bgColor = backGreen, fgColor = faceGreen,modifier=Modifier.fillMaxWidth())
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                        SimpleButton(text = "Custom", bgColor = backGray, fgColor = faceGray,modifier=Modifier.fillMaxWidth())
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Charging Schedule",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    items(schedules.indices.toList()) {
                        item -> Row(modifier = Modifier.padding(vertical = 8.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(color = paleBlue, shape = RoundedCornerShape(12.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(Icons.Rounded.DateRange, "Time", modifier = Modifier, faceBlue)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(vertical = 2.dp).weight(1f)) {
                                    Text(schedules[item].day.joinToString(transform = {D -> shortDay(D)} ), fontWeight = FontWeight.Bold, fontSize = 24.sp, color = faceBlue)
                                    Text("${schedules[item].startTime} - ${schedules[item].endTime}", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Switch(
                                    checked = schedules[item].enabled,
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = faceBlue,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = faceGray
                                        ),
                                    onCheckedChange = {isChecked ->
                                    /*
                                    val target = schedules[item]
                                    for(i in schedules.indices) {
                                        if(i != item) {
                                            scheduleUpdate.add(schedules[i])
                                        } else {
                                            scheduleUpdate.add(ScheduleData(target.day, target.startTime, target.endTime, isChecked))
                                        }
                                    }
                                    schedules = scheduleUpdate
                                    scheduleUpdate.clear()
                                     */
                                })
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                SimpleButton(text = "+ Add New Schedule", bgColor = backBlue, fgColor = faceBlue,modifier=Modifier.fillMaxWidth())
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                    SimpleButton(text = "Start Charging Now", bgColor = backRed, fgColor = faceRed,modifier=Modifier.fillMaxWidth(),
                        icon = IconData("Charging", painter = painterResource(id = drawable.ev_charge))
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier=Modifier.weight(0.2f).padding(0.dp)) {
                    SimpleButton(text = "View Charging History", bgColor = backGreen, fgColor = faceGreen,modifier=Modifier.fillMaxWidth(),
                        icon = IconData("History", vector = Icons.Rounded.Info)
                    )
                }
            }

        }
    }
}

@Composable
fun SimpleButton(text: String, bgColor: Color, modifier: Modifier = Modifier, fgColor: Color = Color.Black, icon: IconData? = null) {
    Box(
        modifier = modifier
            .background(bgColor, shape = RoundedCornerShape(8.dp))
            .clickable { }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (icon != null && (icon.vector != null || icon.painter != null) ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(icon.painter != null) {
                    Icon(
                        painter = icon.painter,
                        tint = fgColor,
                        contentDescription = icon.description
                    )
                }else
                if(icon.vector != null) {
                    Icon(
                        imageVector = icon.vector,
                        tint = fgColor,
                        contentDescription = icon.description
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = fgColor)
            }
        } else {
            Text(text, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = fgColor)
        }
    }
}