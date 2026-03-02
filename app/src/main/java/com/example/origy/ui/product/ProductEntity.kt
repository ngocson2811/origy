package com.example.origy.ui.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productDetail")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val productId:Int,
    val image: Int,
    val stepNumber: Int,
)
