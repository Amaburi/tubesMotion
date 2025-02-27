package com.example.roomcompose.Model

import com.example.roomcompose.Object.RetrofitClient

class GameRepository {
    /**
     * Instance dari RetrofitClient untuk melakukan request API.
     */
    private val apiService = RetrofitClient.instance

    /**
     * API key yang digunakan untuk mengautentikasi permintaan ke layanan API.
     * Gantilah dengan API key yang valid sesuai kebutuhan.
     */
    private val apiKey = "b31ffb9120a849a1b1a290a7232fc98c"

    /**
     * Mengambil daftar game dari API berdasarkan halaman yang diberikan.
     *
     * @param page Nomor halaman yang ingin diambil dari API.
     * @return Daftar game dalam bentuk List<Gamee>.
     * @throws Exception Jika terjadi kesalahan saat melakukan permintaan API.
     */
    suspend fun getGames(page: Int): List<Gamee> {
        return apiService.getGames(apiKey, page).games
    }
}
