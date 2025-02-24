package com.example.roomcompose.Object

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    // Firestore requires a no-arg constructor
    constructor() : this(0, "", 0, "", 0)
}
