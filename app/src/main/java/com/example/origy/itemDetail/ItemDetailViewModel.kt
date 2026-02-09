package com.example.origy.itemDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.R
import com.example.origy.product.ProductEntity
import kotlinx.coroutines.launch


class ItemDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.get(application).ItemDetailDao()
    fun getItems(categoryId: Int): LiveData<List<ItemDetailEntity>> {
        return dao.getByCategory(categoryId)
    }
    fun seedProduct() {
        viewModelScope.launch {
            if (dao.getAll().isEmpty()) {
                val list = listOf(
                    ItemDetailEntity(
                        categoryId = 1,
                        name = "Cleaver",
                        image = R.drawable.image_detail,
                        isNew = true
                    ),
                    ItemDetailEntity(
                        categoryId = 1,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 2,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 3,
                        name = "Cleaver",
                        image = R.drawable.image_detail,
                        isNew = true
                    ),
                    ItemDetailEntity(
                        categoryId = 4,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 5,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 6,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 7,
                        name = "Cleaver",
                        image = R.drawable.image_detail,
                        isNew = true
                    ),
                    ItemDetailEntity(
                        categoryId = 8,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 9,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 10,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 11,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                    ItemDetailEntity(
                        categoryId = 12,
                        name = "Cleaver",
                        image = R.drawable.image_detail
                    ),
                )
                dao.insertAll(list)
            }
        }




    }



    fun getRandomProduct(): LiveData<ItemDetailEntity> = liveData {
        emit(dao.getRandomProduct())
    }




}