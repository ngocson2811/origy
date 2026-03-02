package com.example.origy.ui.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {



    @Query("SELECT * FROM productDetail WHERE productId = :productId ORDER BY stepNumber")
    fun getByProduct(productId: Int): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM productDetail ORDER BY productId, stepNumber")
    suspend fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Query("DELETE FROM productDetail")
    suspend fun deleteAll()

}