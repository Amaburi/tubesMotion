package com.example.roomcompose.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.R
import com.example.roomcompose.utils.Achievement
import com.example.roomcompose.utils.AchievementCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementScreen(
    achievements: List<Achievement>,
    navController: NavController,
    authView: AuthViewModel
) {
    val selectedTab = remember { mutableStateOf(0) }
    val isDarkTheme = remember { mutableStateOf(true) }

    Scaffold(
        containerColor = if (isDarkTheme.value) colorResource(id = R.color.black) else colorResource(
            id = R.color.white
        ),

        // 🔹 TOP BAR
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkTheme.value) Color.Black else Color.White,
                    titleContentColor = if (isDarkTheme.value) Color.White else Color.Black,
                ),
                title = { Text("Achievements", fontWeight = FontWeight.Bold) },
                actions = {

                    IconButton(onClick = { isDarkTheme.value = !isDarkTheme.value }) {
                        Icon(
                            painter = if (isDarkTheme.value) painterResource(id = R.drawable.moon) else painterResource(
                                id = R.drawable.sun
                            ),
                            contentDescription = "Toggle Theme",
                            tint = if (isDarkTheme.value) Color.White else Color.Black
                        )
                    }

                }
            )
        },

        // 🔹 BOTTOM BAR
        bottomBar = {
            BottomAppBar(
                containerColor = if (isDarkTheme.value) Color.Black else Color.White,
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val icons = listOf(
                            Pair(R.drawable.home, "home"),
                            Pair(R.drawable.cart, "cart"),
                            Pair(R.drawable.games, "gamelist"),
                            Pair(R.drawable.trophies, "achievement"),
                            Pair(R.drawable.settings_1, "settings")
                        )

                        icons.forEachIndexed { index, (icon, route) ->
                            IconButton(
                                onClick = {
                                    selectedTab.value = index + 1
                                    navController.navigate(route)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = route,
                                    tint = if (selectedTab.value == index + 1) colorResource(id = R.color.greenpm) else Color.Gray
                                )
                            }
                        }
                    }
                }
            )
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkTheme.value) Color(0xFF121212) else Color(0xFFF5F5F5)) // Dark gray & light gray
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Your Achievements",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (isDarkTheme.value) Color.White else Color.Black,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(achievements) { achievement ->
                        AchievementCard(achievement)
                    }
                }
            }
        }
    }
}
