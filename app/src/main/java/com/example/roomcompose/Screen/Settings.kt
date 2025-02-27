package com.example.roomcompose.Screen

import MyGamesViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Object.Games
import com.example.roomcompose.R

@Composable
fun Settings(viewModel: MyGamesViewModel, navController: NavController, authView: AuthViewModel) {
    val isDarkTheme = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme.value) Color.Black else Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = if (isDarkTheme.value) Color.DarkGray else Color.LightGray)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.games),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "John Doe",
                    fontSize = 22.sp,
                    color = if (isDarkTheme.value) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "johndoe@email.com",
                    fontSize = 14.sp,
                    color = if (isDarkTheme.value) Color.LightGray else Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Options
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = if (isDarkTheme.value) Color.DarkGray else Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SettingsItem(icon = Icons.Default.Send, text = "Dark Mode") {
                    isDarkTheme.value = !isDarkTheme.value
                }
                SettingsItem(icon = Icons.Default.Notifications, text = "Notifications") {}
                SettingsItem(icon = Icons.Default.Person, text = "Language") {}
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SettingsButton(
                text = "Add Dummy Game",
                icon = Icons.Default.Add,
                color = Color.Blue
            ) {
                navController.navigate("addgame")
            }

            SettingsButton(
                text = "Delete All Games",
                icon = Icons.Default.Delete,
                color = Color.Red
            ) {
                viewModel.deleteAllGames()
            }

            SettingsButton(
                text = "Sign Out",
                icon = Icons.Default.ExitToApp,
                color = Color.Gray
            ) {
                authView.signOut()
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            }
            SettingsButton(
                text = "Home",
                icon = Icons.Default.ExitToApp,
                color = Color.Gray
            ) {
                navController.navigate("home")
            }
        }
    }
}

// Reusable Settings Option
@Composable
fun SettingsItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontSize = 18.sp, color = Color.White)
    }
}

// Reusable Button
@Composable
fun SettingsButton(text: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color.copy(alpha = 0.2f),
            contentColor = color
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text, fontSize = 18.sp)
        }
    }
}
