package com.example.origy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.origy.ui.home.category.CategoryDao
import com.example.origy.ui.home.category.CategoryEntity
import com.example.origy.ui.itemDetail.ItemDetailDao
import com.example.origy.ui.itemDetail.ItemDetailEntity
import com.example.origy.ui.product.ProductDao
import com.example.origy.ui.product.ProductEntity

@Database(entities = [
    CategoryEntity:: class,
    ItemDetailEntity::class,
    ProductEntity::class
                     ], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao() : CategoryDao
    abstract fun ItemDetailDao(): ItemDetailDao
    abstract fun ProductDao(): ProductDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "origy_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}