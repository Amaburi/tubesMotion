package com.example.roomcompose.Model

import MyGamesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomcompose.Internal.MyGamesRepository

class MyGamesViewModelFactory(private val repository: MyGamesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyGamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
