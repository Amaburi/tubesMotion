package com.example.roomcompose.Model

import com.example.roomcompose.utils.Game
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results") val games: List<Gamee>
)

data class Gamee(
    val id: Int,
    val name: String,
    @SerializedName("background_image") val imageUrl: String?
)


