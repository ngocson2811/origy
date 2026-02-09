package com.example.origy.itemDetail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDetailDao {

    @Insert
    suspend fun insertAll(list: List<ItemDetailEntity>)

    @Query("SELECT * FROM itemDetail WHERE categoryId = :id")
    fun getByCategory(id: Int): LiveData<List<ItemDetailEntity>>

    @Query("SELECT * FROM itemDetail")
    suspend fun getAll(): List<ItemDetailEntity>

    @Query("UPDATE itemDetail SET isFavorite = :favorite WHERE id = :itemId")
    suspend fun updateFavorite(itemId: Int, favorite: Boolean)

    @Query("SELECT isFavorite FROM itemDetail WHERE id = :itemId")
    suspend fun isFavorite(itemId: Int): Boolean

    @Query("SELECT * FROM itemDetail WHERE isFavorite = 1")
    suspend fun getFavorite(): List<ItemDetailEntity>

    @Query("SELECT * FROM itemDetail ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomProduct(): ItemDetailEntity

    @Query("SELECT * FROM itemDetail WHERE isNew = 1")
    suspend fun getNewItems(): List<ItemDetailEntity>


}