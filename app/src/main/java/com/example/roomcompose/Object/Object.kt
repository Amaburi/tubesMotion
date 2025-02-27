package com.example.roomcompose.Object

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity yang merepresentasikan tabel `gamesinfo` dalam database.
 *
 * @property id ID unik game, dihasilkan secara otomatis.
 * @property name Nama game.
 * @property sales Jumlah penjualan game.
 * @property comments Komentar atau ulasan tentang game.
 * @property price Harga game.
 */
@Entity(tableName = "gamesinfo")
data class Games(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "sales")
    val sales: Int = 0,
    @ColumnInfo(name = "comments")
    val comments: String = "",
    @ColumnInfo(name = "price")
    val price: Int = 0
) {
    /**
     * Konstruktor tanpa argumen yang diperlukan oleh Firestore.
     */
    constructor() : this(0, "", 0, "", 0)
}

