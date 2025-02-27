package com.example.roomcompose

import MyGamesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomcompose.Internal.AuthRepository
import com.example.roomcompose.Internal.GamesDB
import com.example.roomcompose.Internal.MyGamesRepository
import com.example.roomcompose.Model.AuthViewModel
import com.example.roomcompose.Model.AuthViewModelFactory
import com.example.roomcompose.Model.MyGamesViewModelFactory
import com.example.roomcompose.Screen.AchievementScreen
import com.example.roomcompose.Screen.AddGameScreen
import com.example.roomcompose.Screen.CartScreen
import com.example.roomcompose.Screen.GamesScreen
import com.example.roomcompose.Screen.HomeScreen
import com.example.roomcompose.Screen.Settings
import com.example.roomcompose.Screen.SignIn
import com.example.roomcompose.Screen.SignUp
import com.example.roomcompose.ui.theme.RoomcomposeTheme
import com.example.roomcompose.utils.sampleAchievements
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        // Initialize Database & Repository
        val database = GamesDB.getMyGamesDatabase(this)
        val repository = MyGamesRepository(database.GamesDao())

        // Create ViewModel using Factory
        val viewModelFactory = MyGamesViewModelFactory(repository)
        val gamesViewModel = ViewModelProvider(this, viewModelFactory)[MyGamesViewModel::class.java]

        // Initialize Auth Repository & ViewModel
        val authRepository = AuthRepository() // Ensure this is initialized properly
        val authViewModelFactory = AuthViewModelFactory(authRepository)
        val authViewModel = ViewModelProvider(this, authViewModelFactory)[AuthViewModel::class.java]
        setContent {
            val navController = rememberNavController()

            val user = authViewModel.user.collectAsState().value


            NavHost(navController = navController, startDestination = "home") {
                composable("signup") { SignUp(navController, authViewModel) }
                composable("login") { SignIn(navController, authViewModel) }
                composable("home") { HomeScreen(gamesViewModel, navController, authViewModel) }
                composable("settings") { Settings(gamesViewModel, navController, authViewModel) }
                composable("gamelist") { GamesScreen(navController, authViewModel) }
                composable("achievement") { AchievementScreen(sampleAchievements,navController, authViewModel) }
                composable("cart") { CartScreen(navController, authViewModel) }
                composable("addgame") { AddGameScreen(gamesViewModel, navController) }

            }
            //HomeScreen(gamesViewModel)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomcomposeTheme {
        Greeting("Android")
    }
}