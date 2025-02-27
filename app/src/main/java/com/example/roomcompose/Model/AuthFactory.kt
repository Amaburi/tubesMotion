package com.example.roomcompose.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomcompose.Internal.AuthRepository

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    /**
     * Membuat instance ViewModel dari kelas yang diberikan.
     *
     * @param modelClass Class<T> kelas ViewModel yang ingin dibuat.
     * @return T instance dari ViewModel yang diminta.
     * @throws IllegalArgumentException jika kelas yang diminta bukan AuthViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}