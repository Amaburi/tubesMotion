package com.example.roomcompose.Model

import com.example.roomcompose.utils.Game
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results") val games: List<Gamee>
)

/**
 * Data class yang merepresentasikan informasi dasar tentang sebuah game.
 * Digunakan sebagai item dalam daftar games yang dikembalikan oleh Rawg API.
 *
 * @property id Int ID unik dari game.
 * @property name String Nama atau judul game.
 * @property imageUrl String? URL gambar latar belakang game.
 *            Nama asli properti dalam JSON adalah "background_image".
 *            Dapat bernilai null jika game tidak memiliki gambar.
 */
data class Gamee(
    val id: Int,
    val name: String,
    @SerializedName("background_image") val imageUrl: String?
)