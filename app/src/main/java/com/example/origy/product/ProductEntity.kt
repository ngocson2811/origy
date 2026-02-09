package com.example.origy.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productDetail")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId:Int,
    val image: Int,
    val stepNumber: Int,
)
