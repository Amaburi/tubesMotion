package com.example.roomcompose.Internal

import com.example.roomcompose.Model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApiService {
    /**
     * Mengambil daftar game dari Rawg API.
     *
     * @param apiKey String API key yang diperlukan untuk mengakses Rawg API.
     * @param page Int nomor halaman yang ingin diambil (default: 1).
     * @param pageSize Int jumlah item per halaman (default: 20).
     * @return GameResponse objek response yang berisi daftar game dan metadata terkait.
     */
    @GET("games") // untuk mendapatkan query dari endpoint URL "games"
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20
    ): GameResponse
}