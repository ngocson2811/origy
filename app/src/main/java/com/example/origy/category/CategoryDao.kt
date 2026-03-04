package com.example.origy.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {



    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<CategoryEntity>>

    @Query("SELECT COUNT(*) FROM category")
    suspend fun getCount(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: CategoryEntity)


}