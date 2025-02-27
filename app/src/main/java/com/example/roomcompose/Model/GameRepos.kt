package com.example.roomcompose.Model

import com.example.roomcompose.Object.RetrofitClient

class GameRepository {
    private val apiService = RetrofitClient.instance
    private val apiKey = "b31ffb9120a849a1b1a290a7232fc98c"  // Replace with your API key

    suspend fun getGames(page: Int): List<Gamee> {
        return apiService.getGames(apiKey, page).games
    }
}