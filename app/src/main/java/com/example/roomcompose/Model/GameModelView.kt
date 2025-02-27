package com.example.roomcompose.Model


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    /**
     * Repository yang menyediakan akses ke sumber data game.
     */
    private val repository = GameRepository()

    /**
     * State internal yang mutable untuk menyimpan daftar game.
     * Diinisialisasi dengan list kosong.
     */
    private val _games = mutableStateOf<List<Gamee>>(emptyList())

    /**
     * State yang diekspos ke UI untuk mengobservasi daftar game.
     * Tipe immutable untuk mencegah modifikasi dari luar ViewModel.
     */
    val games: State<List<Gamee>> = _games

    /**
     * Blok inisialisasi untuk mengambil data game saat ViewModel dibuat.
     */
    init {
        fetchGames()
    }

    /**
     * Mengambil daftar game dari repository.
     * Metode ini menggunakan coroutine untuk operasi asynchronous dan
     * menangani kemungkinan error dengan try-catch.
     */
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