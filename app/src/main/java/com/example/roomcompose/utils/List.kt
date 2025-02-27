package com.example.roomcompose.utils

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star

import com.example.roomcompose.R

data class Game(
    val id: String,
    val title: String,
    val price: Double,
    val salesCount: Int,
    val imageRes: Int,
    val description: String = "",
)
data class Achievement(
    val title: String,
    val description: String,
    val progress: Float, // 0.0 - 1.0
    val isCompleted: Boolean,
    val iconRes: Int
)
data class Game2(
    val id: String,
    val title: String,
    val price: Double,
    val releaseDate : String,
    val releaseYear: Int,
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

val gameListt = listOf(
    Game(
        id = "1",
        title = "Final Fantasy VII: Rebirth",
        price = 69.99,
        salesCount = 5000000,
        imageRes = R.drawable.ff7,
        description = "Continue Cloud's journey in the second installment of the epic remake trilogy."
    ),
    Game(
        id = "2",
        title = "Dragon Age: The Veilguard",
        price = 69.99,
        salesCount = 4800000,
        imageRes = R.drawable.dragonage,
        description = "A new heroic adventure in BioWare's acclaimed fantasy RPG series."
    ),
    Game(
        id = "3",
        title = "Star Wars Outlaws",
        price = 59.99,
        salesCount = 6200000,
        imageRes = R.drawable.starwars,
        description = "An open-world adventure set between the events of The Empire Strikes Back and Return of the Jedi."
    ),
    Game(
        id = "4",
        title = "Elden Ring: Shadow of the Erdtree",
        price = 39.99,
        salesCount = 8500000,
        imageRes = R.drawable.eldenring,
        description = "The massive expansion to the critically acclaimed Elden Ring."
    ),
    Game(
        id = "5",
        title = "Black Myth: Wukong",
        price = 59.99,
        salesCount = 12000000,
        imageRes = R.drawable.wukong,
        description = "An action RPG based on Chinese mythology and Journey to the West."
    ),
    Game(
        id = "6",
        title = "Senua's Saga: Hellblade II",
        price = 49.99,
        salesCount = 3200000,
        imageRes = R.drawable.hellblade,
        description = "A dark, psychological journey through Norse myth and psychosis."
    ),
    Game(
        id = "7",
        title = "Metaphor: ReFantazio",
        price = 59.99,
        salesCount = 3800000,
        imageRes = R.drawable.metaphor,
        description = "A fantasy RPG from the creators of Persona with unique social simulation elements."
    ),
    Game(
        id = "8",
        title = "Silent Hill 2 Remake",
        price = 59.99,
        salesCount = 3500000,
        imageRes = R.drawable.silenthill2,
        description = "A reimagining of the psychological horror classic with modern visuals and gameplay."
    ),
    Game(
        id = "9",
        title = "Avowed",
        price = 69.99,
        salesCount = 2800000,
        imageRes = R.drawable.avowed,
        description = "An epic first-person fantasy RPG set in the world of Eora from Obsidian Entertainment."
    ),
    Game(
        id = "10",
        title = "Stalker 2: Heart of Chornobyl",
        price = 59.99,
        salesCount = 4100000,
        imageRes = R.drawable.stalker2,
        description = "A post-apocalyptic open-world survival horror game set in the Chornobyl Exclusion Zone."
    ),
    Game(
        id = "11",
        title = "Fable",
        price = 69.99,
        salesCount = 3300000,
        imageRes = R.drawable.fable,
        description = "A new beginning for the legendary RPG series with a fresh take on its fantastical world."
    ),
    Game(
        id = "12",
        title = "Hollow Knight: Silksong",
        price = 29.99,
        salesCount = 5500000,
        imageRes = R.drawable.silksong,
        description = "The long-awaited sequel to the acclaimed metroidvania, following Hornet in a new kingdom."
    ),
    Game(
        id = "13",
        title = "Assassin's Creed Shadows",
        price = 69.99,
        salesCount = 6800000,
        imageRes = R.drawable.acshadows,
        description = "Experience feudal Japan through the eyes of dual protagonists, a shinobi and a samurai."
    ),
    Game(
        id = "14",
        title = "Indiana Jones and the Great Circle",
        price = 59.99,
        salesCount = 4700000,
        imageRes = R.drawable.indy,
        description = "A globe-trotting adventure with the legendary archaeologist at the height of his career."
    ),
    Game(
        id = "15",
        title = "Helldivers 2",
        price = 39.99,
        salesCount = 7000000,
        imageRes = R.drawable.helldivers2,
        description = "A thrilling cooperative shooter where players fight for democracy across hostile alien worlds."
    ),

    Game(
        id = "16",
        title = "Dune: Awakening",
        price = 49.99,
        salesCount = 3900000,
        imageRes = R.drawable.duneawakening,
        description = "A survival MMO set in Frank Herbert's Dune universe on the unforgiving planet Arrakis."
    ),
    Game(
        id = "17",
        title = "Monster Hunter Wilds",
        price = 69.99,
        salesCount = 7200000,
        imageRes = R.drawable.mhwilds,
        description = "The next evolution of the Monster Hunter series with a vast new ecosystem to explore."
    )
)

val upcomingGames = listOf(
    Game2(
        id = "1",
        title = "Kingdom Come: Deliverance II",
        price = 59.99,
        releaseDate = "February 4, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.kingdom,
        description = "Continue the journey of Henry in this open-world RPG, navigating the complexities of a civil war in Central Bohemia."
    ),
    Game2(
        id = "2",
        title = "Sid Meier's Civilization VII",
        price = 49.99,
        releaseDate = "February 11, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.vii,
        description = "Build and expand your empire, challenging historical leaders in the latest installment of the iconic strategy series."
    ),
    Game2(
        id = "3",
        title = "Like a Dragon: Pirate Yakuza in Hawaii",
        price = 59.99,
        releaseDate = "February 20, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.yakuza,
        description = "Join ex-yakuza Goro Majima on a wild adventure as a pirate captain, engaging in naval battles and quirky side quests in Hawaii."
    ),
    Game2(
        id = "4",
        title = "Wanderstop",
        price = 29.99,
        releaseDate = "March 11, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.wanderstop,
        description = "Manage a tea shop in an enchanted forest as Alta, a fallen fighter confronting her past in this cozy narrative game."
    ),
    Game2(
        id = "5",
        title = "Split Fiction",
        price = 39.99,
        releaseDate = "March 6, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.split,
        description = "A cooperative adventure where two writers are trapped inside their own stories, requiring teamwork to return to reality."
    ),
    Game2(
        id = "6",
        title = "Doom: The Dark Ages",
        price = 59.99,
        releaseDate = "May 15, 2025",
        releaseYear = 2025,
        imageRes = R.drawable.doom,
        description = "Experience the origin story of the Doom Slayer in a techno-medieval universe, battling demonic forces in intense combat."
    ),
    Game2(
        id = "7",
        title = "Borderlands 4",
        price = 69.99,
        releaseDate = "2025",
        releaseYear = 2025,
        imageRes = R.drawable.borderland,
        description = "Explore the new planet of Kairos with four fresh Vault Hunters in this looter-shooter, featuring an open world and chaotic action."
    ),
    Game2(
        id = "8",
        title = "Grand Theft Auto VI",
        price = 79.99,
        releaseDate = "Fall 2025",
        releaseYear = 2025,
        imageRes = R.drawable.gta6,
        description = "Return to Vice City in this highly anticipated installment, following a dynamic duo on a crime spree in a vast open world."
    )
)



val sampleAchievements = listOf(
    Achievement(
        title = "First Purchase",
        description = "Buy your first game",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.cart
    ),
    Achievement(
        title = "Collector",
        description = "Own 10 games in your library",
        progress = 0.7f,
        isCompleted = false,
        iconRes = R.drawable.star
    ),
    Achievement(
        title = "Veteran Gamer",
        description = "Play for 100 hours",
        progress = 0.4f,
        isCompleted = false,
        iconRes = R.drawable.console
    ),
    Achievement(
        title = "Social Player",
        description = "Add 5 friends",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.people
    ),
    Achievement(
        title = "Max Level",
        description = "Reach the highest rank in the app",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.fire
    ),
    Achievement(
        title = "Big Spender",
        description = "Spend over \$500 on games.",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.money
    ),
    Achievement(
        title = "SpeedRunner",
        description = "Finish any game under 1 hour.",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.rocket
    ),
    Achievement(
        title = "Marathon Gamer",
        description = "Play a game for 100+ hours.",
        progress = 1f,
        isCompleted = true,
        iconRes = R.drawable.hour
    ),
)