package com.example.roomcompose.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, authView: AuthViewModel) {
    var cartItems by remember { mutableStateOf(sampleCartItems) }
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
                title = { Text("Cart", fontWeight = FontWeight.Bold) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = "Your Cart",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item) { removedItem ->
                        cartItems = cartItems.filter { it.id != removedItem.id }
                    }
                }
            }

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE2F163))
            ) {
                Text(
                    text = "Checkout",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}

@Composable
fun CartItemCard(item: CartItem, onRemove: (CartItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF232222)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "Game Image",
                modifier = Modifier.size(80.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${'$'}${item.price}",
                    color = Color(0xFFE2F163),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Button(
                onClick = { onRemove(item) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Remove", color = Color.White)
            }
        }
    }
}

// Sample data
data class CartItem(val id: Int, val name: String, val price: Double, val imageRes: Int)

val sampleCartItems = listOf(
    CartItem(1, "Elden Ring", 59.99, R.drawable.eldenring),
    CartItem(2, "Star Wars", 39.99, R.drawable.starwars),
    CartItem(3, "Zelda", 49.99, R.drawable.zelda),
    CartItem(4, "Hell Divers 2", 49.99, R.drawable.helldivers2),
    CartItem(5, "Hogwarts Legacy", 49.99, R.drawable.hogwarts)
)
