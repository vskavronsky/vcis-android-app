package com.automotive.bootcamp.userprofiles.presentation.screen

import UserCard
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.presentation.state.UserProfilesUIState
import compose_theme.theme.IntelliAutoHMITheme
import androidx.compose.material3.Button
import com.automotive.bootcamp.userprofiles.domain.model.NavigationFavorite

@Composable
fun UserProfilesScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserProfilesViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.users.size > 0) {
        state.currentUser = state.users[0]
    }

    BackHandler {
        onClose()
    }

    UserProfilesView(
        state = state,
        onUserProfilesClick = {
            viewModel.userProfilesClick()
        },
        onUserProfilesSuspendClick = {
            viewModel.userProfilesSuspendClick()
        },
        modifier = modifier,
    )
}

@Composable
private fun BodyCardView(state: UserProfilesUIState, color: Color, description: String) {
    Card(
        modifier = Modifier.size(width = 180.dp, height = 180.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
    ) {
        Text(
            text = description,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SettingBox(label: String, color: Color) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .background(color, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(label, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Gray)) {
                Text("Adjust")
            }
        }
    }
}

@Composable
fun PersonalizationSettings(state: UserProfilesUIState, modifier: Modifier) {
    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Face, contentDescription = null, tint = Color(0xFF4CB6A7))
            Spacer(modifier = Modifier.size(width = 8.dp, height = 8.dp))
            Text(
                text = "Personalization Settings",
                textAlign = TextAlign.Left,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Settings grid
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SettingBox("Seat", Color(0xFFBFC6F7))
            SettingBox("Mirrors", Color(0xFFFFE1B3))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SettingBox("Climate", Color(0xFFB7E6E0))
            SettingBox("Media", Color(0xFFFFC7D9))
        }
    }
}

@Composable
fun NavigationFavorites(
    state: UserProfilesUIState,
    modifier: Modifier,
    onAddFavorite: () -> Unit = {}
) {
    val favorites: List<NavigationFavorite> = if (state.currentUser != null && state.configuration.containsKey(state.currentUser!!.id)) {
        state.configuration[state.currentUser!!.id]!!.favorites
    } else {
       emptyList()
    }

    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Place, contentDescription = null, tint = Color(0xFFFFE1B3))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Navigation Favorites")
        }
        Spacer(modifier = Modifier.height(16.dp))
        favorites.forEach { favorite ->
            FavoriteItem(favorite, modifier)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onAddFavorite,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBFC6F7))
        ) {
            Text("+ Add Favorite")
        }
    }
}

@Composable
fun FavoriteItem(favorite: NavigationFavorite, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Icon(favorite.icon, contentDescription = null, tint = favorite.color)
        Spacer(modifier = Modifier.width(8.dp))
        Text(favorite.destination, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = favorite.color)
        ) {
            Text("Set", color = Color.White)
        }
    }
}

@Composable
private fun UserProfilesBodyView(state: UserProfilesUIState) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            PersonalizationSettings(state, modifier = Modifier.weight(1f))
            NavigationFavorites(state, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun FooterCardView(state: UserProfilesUIState, icon: ImageVector, description: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier.size(width = 156.dp, height = 156.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .size(width = 50.dp, height = 50.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = description,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun UserProfilesFooterView(
    state: UserProfilesUIState,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FooterCardView(state, Icons.Rounded.Settings, "Key Settings")
        FooterCardView(state, Icons.Rounded.Notifications, "Notifications")
        FooterCardView(state, Icons.Rounded.Lock, "Security")
        FooterCardView(state, Icons.Rounded.Settings, "System")
        FooterCardView(state, Icons.Rounded.Face, "Help")
    }
}

@Composable
private fun UserProfilesView(
    state: UserProfilesUIState,
    onUserProfilesClick: () -> Unit,
    onUserProfilesSuspendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UsersRouletteView(state = state)
            UserProfilesBodyView(state = state)
            UserProfilesFooterView(state = state)
        }
    }
}

@Composable
fun AddUserCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier.size(width = 200.dp, height = 200.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun UsersRouletteView(state: UserProfilesUIState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.users) { user ->
                val isCurrent = state.currentUser != null && state.currentUser!!.id == user.id
                UserCard(user, isCurrent, onSelect = { state.currentUser = user })
            }
        }
        AddUserCard()
    }
}

@Preview("default")
@Preview("dark theme")
@Composable
private fun DefaultUserProfilesScreenPreview() {
    IntelliAutoHMITheme {
        Surface {
            UserProfilesView(
                state = UserProfilesUIState(
                    "empty",
                    "suspend",
                    temp = TODO(),
                    users = TODO(),
                    warnings = TODO(),
                    configuration = TODO(),
                    currentUser = null,
                ),
                onUserProfilesClick = {},
                onUserProfilesSuspendClick = {},
            )
        }
    }
}
