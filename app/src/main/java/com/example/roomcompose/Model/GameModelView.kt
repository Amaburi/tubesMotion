package com.example.roomcompose.Model


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val repository = GameRepository()
    private val _games = mutableStateOf<List<Gamee>>(emptyList())
    val games: State<List<Gamee>> = _games

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            try {
                _games.value = repository.getGames(1)
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching games", e)
            }
        }
    }
}
