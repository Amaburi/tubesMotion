package com.example.roomcompose.Model

import MyGamesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomcompose.Internal.MyGamesRepository

class MyGamesViewModelFactory(private val repository: MyGamesRepository) : ViewModelProvider.Factory {
    /**
     * Membuat instance dari ViewModel berdasarkan kelas yang diminta.
     *
     * @param modelClass Kelas ViewModel yang ingin dibuat.
     * @return Instance dari ViewModel yang diminta.
     * @throws IllegalArgumentException Jika kelas ViewModel tidak dikenal.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyGamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

