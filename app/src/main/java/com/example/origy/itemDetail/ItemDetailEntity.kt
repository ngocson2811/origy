package com.example.origy.itemDetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("itemDetail")
data class ItemDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: Int,
    val name: String,
    val image: Int,
    val isFavorite: Boolean = false,
    val isNew: Boolean = false
)
