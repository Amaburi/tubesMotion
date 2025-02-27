package com.example.roomcompose.Object

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyGamesDao {
    /**
     * Mengambil semua game yang tersimpan di database.
     *
     * @return Flow<List<Games>> yang akan diperbarui secara otomatis ketika data berubah.
     */
    @Query("SELECT * FROM gamesinfo")
    fun getAll(): Flow<List<Games>>

    /**
     * Menambahkan informasi game baru ke dalam database.
     *
     * @param game Objek game yang akan disimpan.
     * @return ID game yang baru dimasukkan.
     */
    @Insert
    suspend fun insertGamesInfo(game: Games): Long

    /**
     * Menghapus semua data game yang tersimpan dalam database.
     */
    @Query("DELETE FROM gamesinfo")
    suspend fun deleteAllGames()
}