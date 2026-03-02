package com.example.origy.ui.itemDetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("itemDetail")
data class ItemDetailEntity(
    @PrimaryKey
    val id: Int,
    val categoryId: Int,
    val name: String,
    val image: Int,
    val isNew: Boolean = false,
    val isFavorite: Boolean = false
)
