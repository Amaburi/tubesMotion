package com.example.roomcompose.Object

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyGamesDao {
    @Query("SELECT * FROM gamesinfo")
    fun getAll(): Flow<List<Games>>

    @Insert
    suspend fun insertGamesInfo(game: Games): Long

    @Query("DELETE FROM gamesinfo")
    suspend fun deleteAllGames()
}
