package com.example.roomcompose.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcompose.Internal.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(authRepository.getCurrentUser())
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow() // ✅ Expose as immutable

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _user.value = firebaseAuth.currentUser // ✅ Automatically update user state
    }

    init {
        authRepository.addAuthStateListener(authStateListener) // ✅ Start listening
    }

    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val result = authRepository.signUp(email, password)
            onResult(result.isSuccess, result.exceptionOrNull()?.message)
        }
    }

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val result = authRepository.signIn(email, password)
            onResult(result.isSuccess, result.exceptionOrNull()?.message)
        }
    }

    fun signOut() {
        authRepository.signOut()
        authRepository.removeAuthStateListener(authStateListener)
        _user.value = null
    }

    override fun onCleared() {
        super.onCleared()
        authRepository.removeAuthStateListener(authStateListener) // ✅ Cleanup listener
    }
}

