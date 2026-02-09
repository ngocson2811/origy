package com.example.origy.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.origy.itemDetail.ItemDetailEntity

@Dao
interface ProductDao {

    @Insert
    suspend fun insertAll(list: List<ProductEntity>)

    @Query("SELECT * FROM productDetail WHERE productId = :productId ORDER BY stepNumber")
    fun getByProduct(productId: Int): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM productDetail")
    suspend fun getAll(): List<ProductEntity>



}