package com.example.roomcompose.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.roomcompose.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Model.GameViewModel
import com.example.roomcompose.Model.Gamee
import com.example.roomcompose.utils.SwipeableGameCards2
import com.example.roomcompose.utils.SwipeableGameCards3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(navController: NavController, authView: AuthViewModel) {
    val viewModel: GameViewModel = viewModel()
    val games = viewModel.games.value
    val user by authView.user.collectAsState()
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
                title = { Text("Games", fontWeight = FontWeight.Bold) },
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
        if (games.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        "Popular Games",
                        fontSize = 20.sp,
                        color = if (isDarkTheme.value)colorResource(id = R.color.white) else colorResource(id = R.color.black)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(games) { game ->
                            GameItem(game)
                        }
                    }
                    Text(
                        "Latest Release",
                        fontSize = 20.sp,
                        color = if (isDarkTheme.value)colorResource(id = R.color.white) else colorResource(id = R.color.black)
                    )
                    SwipeableGameCards2()
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Upcoming Games",
                        fontSize = 20.sp,
                        color = if (isDarkTheme.value)colorResource(id = R.color.white) else colorResource(id = R.color.black)
                    )
                    SwipeableGameCards3()
                }

            }
        }
    }
}

@Composable
fun GameItem(game: Gamee) {
    Card(
        modifier = Modifier
            .width(150.dp)  // Fixed width for consistent card size
            .height(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Game image takes 70% of the card height
            game.imageUrl?.let { url ->
                val painter = rememberAsyncImagePainter(url)
                Image(
                    painter = painter,
                    contentDescription = "Game cover for ${game.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f),
                    contentScale = ContentScale.Crop
                )
            } ?: Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No image available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Game title takes 30% of the card height
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(8.dp),  // Reduced padding to give more space for text
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleSmall,  // Smaller text size for better fit
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}