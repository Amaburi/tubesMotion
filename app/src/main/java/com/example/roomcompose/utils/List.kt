package com.example.roomcompose.utils

import com.example.roomcompose.R

data class Game(
    val id: String,
    val title: String,
    val price: Double,
    val salesCount: Int,
    val imageRes: Int,
    val description: String = "",
)

val gamesList = listOf(
    Game(
        id = "1",
        title = "The Witcher 3: Wild Hunt",
        price = 39.99,
        salesCount = 50000000,
        imageRes = R.drawable.thewitcher,
        description = "An open-world RPG filled with monsters, magic, and deep storytelling."
    ),
    Game(
        id = "2",
        title = "Red Dead Redemption 2",
        price = 49.99,
        salesCount = 57000000,
        imageRes = R.drawable.rdr2,
        description = "A Western epic set in a stunning, immersive open world."
    ),
    Game(
        id = "3",
        title = "The Legend of Zelda: Breath of the Wild",
        price = 59.99,
        salesCount = 35000000,
        imageRes = R.drawable.zelda,
        description = "An action-adventure masterpiece redefining open-world exploration."
    ),
    Game(
        id = "4",
        title = "Elden Ring",
        price = 59.99,
        salesCount = 23000000,
        imageRes = R.drawable.elden,
        description = "A vast, dark fantasy world crafted by Hidetaka Miyazaki and George R.R. Martin."
    ),
    Game(
        id = "5",
        title = "Grand Theft Auto V",
        price = 29.99,
        salesCount = 195000000,
        imageRes = R.drawable.gtav,
        description = "A massive open-world action-adventure game set in Los Santos."
    ),
    Game(
        id = "6",
        title = "God of War Ragnarok",
        price = 69.99,
        salesCount = 11000000,
        imageRes = R.drawable.gow,
        description = "Kratos and Atreus embark on a mythic journey through Norse realms."
    ),
    Game(
        id = "7",
        title = "Horizon Forbidden West",
        price = 59.99,
        salesCount = 10000000,
        imageRes = R.drawable.horizon,
        description = "Aloy returns in a breathtaking post-apocalyptic adventure."
    )
)
