package com.example.roomcompose.Screen

import MyGamesViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.roomcompose.Object.Games
import com.example.roomcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MyGamesViewModel) {
    val gamesList by viewModel.allGames.collectAsState(initial = emptyList())
    var gameNameInput by remember { mutableStateOf("") }
    var salesInput by remember { mutableStateOf(0) }
    var commentsInput by remember { mutableStateOf("") }
    var priceInput by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = colorResource(id = R.color.greenpm),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.black),
                    titleContentColor = colorResource(id = R.color.white),
                ),
                title = {
                    Text("Orcas Games")
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(gamesList, key = { it.id }) { games ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.black),
                        ),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "PlaceHolder"
                            )
                            Column {
                                Text(
                                    color = colorResource(id = R.color.white),
                                    text = games.name
                                )
                                Text(
                                    color = colorResource(id = R.color.white),
                                    text = "Price: \$${games.price}"
                                )
                                Text(
                                    color = colorResource(id = R.color.white),
                                    text = "Sales: ${games.sales}"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(containerColor = colorResource(id = R.color.black)),
                onClick = {
                    val dummyGame = Games(
                        name = "CyberQuest",
                        sales = 5000,
                        comments = "Amazing open-world game!",
                        price = 59
                    )
                    viewModel.insertGame(dummyGame)
                }) {
                Text(
                    color = colorResource(id = R.color.white),
                    text = "Add Dummy Game"
                )
            }
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(containerColor = colorResource(id = R.color.black)),
                onClick = {
                    viewModel.deleteAllGames()
                }) {
                Text(
                    color = colorResource(id = R.color.white),
                    text = "Delete All Games"
                )
            }
        }
    }
}