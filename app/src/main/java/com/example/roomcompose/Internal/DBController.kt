package com.example.roomcompose.Internal

import com.example.roomcompose.Object.Games
import com.example.roomcompose.Object.MyGamesDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MyGamesRepository(private val myGamesDao: MyGamesDao) {
    private val firestore = FirebaseFirestore.getInstance()
    private val gamesCollection = firestore.collection("games")

    /**
     * Mendapatkan semua games dari database lokal.
     *
     * @return Flow<List<Games>> aliran data yang berisi daftar semua games.
     */
    fun getAll() = myGamesDao.getAll()

    /**
     * Menyimpan informasi game ke database lokal dan Firestore.
     * Metode ini menyimpan game ke Room terlebih dahulu untuk mendapatkan ID,
     * kemudian menggunakan ID tersebut untuk menyimpan ke Firestore.
     *
     * @param game Games objek game yang akan disimpan.
     */
    suspend fun insertGames(game: Games) {
        val gameId = myGamesDao.insertGamesInfo(game) // Insert and get Room ID
        val newGame = game.copy(id = gameId.toInt())  // Update with generated ID

        withContext(Dispatchers.IO) {
            gamesCollection.document(newGame.id.toString()).set(newGame).await()
        }
    }

    /**
     * Menghapus semua games dari database lokal dan Firestore.
     */
    suspend fun deleteAllGames() {
        myGamesDao.deleteAllGames()
        withContext(Dispatchers.IO) {
            val snapshot = gamesCollection.get().await()
            snapshot.documents.forEach { it.reference.delete() }
        }
    }

    /**
     * Melakukan sinkronisasi data dari Firestore ke database lokal.
     * Metode ini mendengarkan perubahan pada koleksi Firestore dan
     * menyinkronkan database lokal ketika ada perubahan.
     */
    fun syncFromFirestore() {
        gamesCollection.addSnapshotListener { snapshot, error ->
            if (error != null || snapshot == null) return@addSnapshotListener

            val gamesList = snapshot.documents.mapNotNull { it.toObject(Games::class.java) }

            CoroutineScope(Dispatchers.IO).launch {
                myGamesDao.deleteAllGames() // Clear local data
                gamesList.forEach { myGamesDao.insertGamesInfo(it) }
            }
        }
    }
}

