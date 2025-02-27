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

    /**
     * MutableStateFlow internal untuk menyimpan informasi pengguna yang sedang login.
     * Nilai awal diambil dari authRepository.getCurrentUser().
     */
    private val _user = MutableStateFlow<FirebaseUser?>(authRepository.getCurrentUser())

    /**
     * StateFlow yang diekspos untuk mengobservasi status login pengguna.
     * Tipe immutable untuk mencegah modifikasi dari luar ViewModel.
     */
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow() // ✅ Expose as immutable

    /**
     * Listener untuk memantau perubahan status autentikasi dari Firebase.
     * Secara otomatis memperbarui _user ketika status autentikasi berubah.
     */
    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _user.value = firebaseAuth.currentUser // ✅ Automatically update user state
    }

    /**
     * Blok inisialisasi untuk mendaftarkan authStateListener saat ViewModel dibuat.
     */
    init {
        authRepository.addAuthStateListener(authStateListener) // ✅ Start listening
    }

    /**
     * Melakukan pendaftaran pengguna baru dengan email dan password.
     *
     * @param email String alamat email untuk pendaftaran.
     * @param password String kata sandi untuk pendaftaran.
     * @param onResult Function callback yang dipanggil setelah operasi selesai.
     *                 Parameter pertama (Boolean) menunjukkan keberhasilan operasi.
     *                 Parameter kedua (String?) berisi pesan error jika operasi gagal.
     */
    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val result = authRepository.signUp(email, password)
            onResult(result.isSuccess, result.exceptionOrNull()?.message)
        }
    }

    /**
     * Melakukan login pengguna dengan email dan password.
     *
     * @param email String alamat email pengguna.
     * @param password String kata sandi pengguna.
     * @param onResult Function callback yang dipanggil setelah operasi selesai.
     *                 Parameter pertama (Boolean) menunjukkan keberhasilan operasi.
     *                 Parameter kedua (String?) berisi pesan error jika operasi gagal.
     */
    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val result = authRepository.signIn(email, password)
            onResult(result.isSuccess, result.exceptionOrNull()?.message)
        }
    }

    /**
     * Melakukan logout pengguna yang sedang aktif.
     * Metode ini juga menghapus authStateListener dan mengosongkan nilai _user.
     */
    fun signOut() {
        authRepository.signOut()
        authRepository.removeAuthStateListener(authStateListener)
        _user.value = null
    }

    /**
     * Metode yang dipanggil ketika ViewModel dihancurkan.
     * Membersihkan authStateListener untuk mencegah memory leak.
     */
    override fun onCleared() {
        super.onCleared()
        authRepository.removeAuthStateListener(authStateListener) // ✅ Cleanup listener
    }
}

