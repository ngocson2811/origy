package com.example.origy.ui.itemDetail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDetailDao {



    @Query("SELECT COUNT(*) FROM itemDetail WHERE categoryId = :categoryId")
    suspend fun countByCategory(categoryId: Int): Int

    @Query("SELECT * FROM itemDetail WHERE categoryId = :id")
    fun getByCategory(id: Int): LiveData<List<ItemDetailEntity>>


    @Query("SELECT COUNT(*) FROM itemDetail WHERE id = :id")
    suspend fun exists(id: Int): Int

    @Query("DELETE FROM itemDetail WHERE id NOT IN (:ids)")
    suspend fun deleteItemsNotIn(ids: List<Int>)



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




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemDetailEntity)

    @Query("""
    UPDATE itemDetail
    SET image = :image,
        isNew = :isNew,
        name = :name
    WHERE id = :id
""")
    suspend fun updateSeedData(
        id: Int,
        image: Int,
        isNew: Boolean,
        name: String
    )


}