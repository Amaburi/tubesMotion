package com.example.roomcompose.Screen

import MyGamesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Object.Games
import com.example.roomcompose.R
import com.example.roomcompose.utils.CardGametwo

import com.example.roomcompose.utils.SwipeableGameCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MyGamesViewModel, navController: NavController, authView: AuthViewModel) {
    val gamesList by viewModel.allGames.collectAsState(initial = emptyList())
    val selectedTab = remember { mutableStateOf(0) }
    var currentIndex by remember { mutableStateOf(0) }

    val user by authView.user.collectAsState()

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
                title = { Text("Home", fontWeight = FontWeight.Bold) },
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
                        if (user != null) {
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
                        } else {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("login")
                                },
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Text(
                                    "Login",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(
                                        id = R.color.black
                                    )
                                )
                            }
                        }
                    }
                }
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Featured & Recommend", fontSize = 35.sp, color = if (isDarkTheme.value) colorResource(id = R.color.white) else colorResource(id = R.color.black)
            )
            SwipeableGameCards()
            Row() {
                Text(
                    "All Games", fontSize = 20.sp, color = if (isDarkTheme.value) colorResource(id = R.color.white) else colorResource(id = R.color.black)
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "",
                        tint = if (isDarkTheme.value)Color.White else colorResource(id = R.color.black),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(bottom = 15.dp)
                    )
                }
            }
            if (gamesList.isNotEmpty()) {
                CardGametwo(
                    game = gamesList[currentIndex],
                    onLeftClick = {
                        if (currentIndex > 0) {
                            currentIndex -= 1
                        }
                    },
                    onRightClick = {
                        if (currentIndex < gamesList.size - 1) {
                            currentIndex += 1
                        }
                    },
                    isDarkTheme.value
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}
