package com.example.roomcompose.Internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomcompose.Object.Games
import com.example.roomcompose.Object.MyGamesDao

@Database(entities = [Games::class], version = 1)
abstract class GamesDB: RoomDatabase(){
    abstract fun GamesDao(): MyGamesDao
    companion object {
        @Volatile
        private var Instance: GamesDB? = null

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