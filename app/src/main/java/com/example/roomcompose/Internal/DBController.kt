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

    fun getAll() = myGamesDao.getAll()

    suspend fun insertGames(game: Games) {
        val gameId = myGamesDao.insertGamesInfo(game) // Insert and get Room ID
        val newGame = game.copy(id = gameId.toInt())  // Update with generated ID

        withContext(Dispatchers.IO) {
            gamesCollection.document(newGame.id.toString()).set(newGame).await()
        }
    }



    suspend fun deleteAllGames() {
        myGamesDao.deleteAllGames()
        withContext(Dispatchers.IO) {
            val snapshot = gamesCollection.get().await()
            snapshot.documents.forEach { it.reference.delete() }
        }
    }


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

