package com.example.roomcompose

import MyGamesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.roomcompose.Internal.GamesDB
import com.example.roomcompose.Internal.MyGamesRepository
import com.example.roomcompose.Model.MyGamesViewModelFactory
import com.example.roomcompose.Screen.HomeScreen
import com.example.roomcompose.Screen.SignUp
import com.example.roomcompose.ui.theme.RoomcomposeTheme
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
        val viewModel = ViewModelProvider(this, viewModelFactory)[MyGamesViewModel::class.java]

        setContent {
            SignUp()
            //HomeScreen(viewModel)
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