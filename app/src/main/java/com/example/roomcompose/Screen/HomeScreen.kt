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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Object.Games
import com.example.roomcompose.R
import com.example.roomcompose.utils.CardGametwo
import com.example.roomcompose.utils.Game
import com.example.roomcompose.utils.GameCard
import com.example.roomcompose.utils.SwipeableGameCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MyGamesViewModel, navController: NavController, authView: AuthViewModel) {
    val gamesList by viewModel.allGames.collectAsState(initial = emptyList())
    val selectedTab = remember { mutableStateOf(0) }
    var currentIndex by remember { mutableStateOf(0) }

    val user by authView.user.collectAsState()

    Scaffold(containerColor = colorResource(id = R.color.black), topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = colorResource(id = R.color.black),
                titleContentColor = colorResource(id = R.color.white),
            ),
            title = {
                Text("Orcas Games")
            },
            actions = {
                if (user != null) {
                    IconButton(onClick = {
                        selectedTab.value = 5
                        navController.navigate("settings")
                    }) {
                        Icon(
                            painterResource(id = R.drawable.settings_1),
                            contentDescription = "Settings",
                            tint = if (selectedTab.value == 5) colorResource(id = R.color.greenpm) else Color.White
                        )
                    }
                }
            }
        )
    }, bottomBar = {
        BottomAppBar(
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (user != null) {
                        IconButton(
                            onClick = {
                                selectedTab.value = 1
                                navController.navigate("home")
                            }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.home),
                                contentDescription = "",
                                tint = if (selectedTab.value == 1) colorResource(id = R.color.greenpm) else Color.White
                            )
                        }



                        IconButton(onClick = {
                            selectedTab.value = 2
                            navController.navigate("cart")
                        }) {
                            Icon(
                                painterResource(id = R.drawable.cart),
                                contentDescription = "Cart",
                                tint = if (selectedTab.value == 2) colorResource(id = R.color.greenpm) else Color.White
                            )
                        }



                        IconButton(onClick = { selectedTab.value = 3 }) {
                            Icon(
                                painterResource(id = R.drawable.games),
                                contentDescription = "Games",
                                tint = if (selectedTab.value == 3) colorResource(id = R.color.greenpm) else Color.White
                            )
                        }


                        IconButton(onClick = { selectedTab.value = 4 }) {
                            Icon(
                                painterResource(id = R.drawable.trophies),
                                contentDescription = "Trophies",
                                tint = if (selectedTab.value == 4) colorResource(id = R.color.greenpm) else Color.White
                            )
                        }


                        IconButton(onClick = {
                            selectedTab.value = 5
                            navController.navigate("settings")
                        }) {
                            Icon(
                                painterResource(id = R.drawable.settings_1),
                                contentDescription = "Settings",
                                tint = if (selectedTab.value == 5) colorResource(id = R.color.greenpm) else Color.White
                            )
                        }

                    }else{
                        OutlinedButton(
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = colorResource(id = R.color.black)),
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                navController.navigate("login")
                            }) {
                            Text(
                                color = colorResource(id = R.color.white),
                                text = "Login"
                            )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            containerColor = colorResource(id = R.color.black)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Featured & Recommend", fontSize = 35.sp, color = colorResource(id = R.color.white)
            )
            SwipeableGameCards()
            Row() {
                Text(
                    "All Games", fontSize = 20.sp, color = colorResource(id = R.color.white)
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "",
                        tint = Color.White,
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
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}
