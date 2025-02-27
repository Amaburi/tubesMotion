package com.example.roomcompose.Internal

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    /**
     * Mendaftarkan pengguna baru dengan email dan password.
     *
     * @param email String alamat email yang akan digunakan untuk pendaftaran.
     * @param password String kata sandi yang akan digunakan untuk pendaftaran.
     * @return Result<Boolean> hasil operasi pendaftaran; sukses dengan nilai true jika berhasil,
     *         atau kegagalan dengan exception jika gagal.
     */
    suspend fun signUp(email: String, password: String): Result<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Melakukan login pengguna dengan email dan password.
     *
     * @param email String alamat email yang terdaftar.
     * @param password String kata sandi untuk akun tersebut.
     * @return Result<Boolean> hasil operasi login; sukses dengan nilai true jika berhasil,
     *         atau kegagalan dengan exception jika gagal.
     */
    suspend fun signIn(email: String, password: String): Result<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Melakukan logout pengguna yang sedang aktif.
     */
    fun signOut() {
        auth.signOut()
    }

    /**
     * Mendapatkan informasi pengguna yang sedang login.
     *
     * @return FirebaseUser? objek pengguna yang sedang login atau null jika tidak ada.
     */
    fun getCurrentUser() = auth.currentUser

    /**
     * Menambahkan listener untuk memantau perubahan status autentikasi.
     *
     * @param listener AuthStateListener yang akan dipanggil ketika status autentikasi berubah.
     */
    fun addAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.addAuthStateListener(listener)
    }

    /**
     * Menghapus listener yang sebelumnya ditambahkan untuk memantau perubahan status autentikasi.
     *
     * @param listener AuthStateListener yang akan dihapus.
     */
    fun removeAuthStateListener(listener: FirebaseAuth.AuthStateListener) {
        auth.removeAuthStateListener(listener)
    }
}