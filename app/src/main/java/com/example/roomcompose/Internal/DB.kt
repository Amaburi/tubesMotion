package com.example.roomcompose.Internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomcompose.Object.Games
import com.example.roomcompose.Object.MyGamesDao

@Database(entities = [Games::class], version = 1)
abstract class GamesDB: RoomDatabase() {
    /**
     * Memberikan akses ke Data Access Object untuk entitas Games.
     *
     * @return MyGamesDao instance untuk melakukan operasi database pada entitas Games.
     */
    abstract fun GamesDao(): MyGamesDao

    companion object {
        /**
         * Instance singleton dari database.
         * Anotasi @Volatile memastikan bahwa perubahan pada Instance segera terlihat oleh thread lain.
         */
        @Volatile
        private var Instance: GamesDB? = null

        /**
         * Mendapatkan atau membuat instance database Games.
         * Implementasi singleton dengan double-checking lock untuk thread safety.
         *
         * @param context Context aplikasi yang digunakan untuk inisialisasi database.
         * @return GamesDB instance database yang dapat digunakan untuk operasi database.
         */
        fun getMyGamesDatabase(context: Context): GamesDB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = GamesDB::class.java,
                    name = "gamesinfo"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}