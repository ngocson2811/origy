package com.example.origy.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.origy.R
import com.example.origy.database.AppDatabase
import kotlinx.coroutines.launch


class ProductViewModel (application: Application) : AndroidViewModel(application){
    private val dao = AppDatabase.get(application).ProductDao()
    private val itemDao = AppDatabase.get(application).ItemDetailDao()
    val favorite = MutableLiveData<Boolean>()
    fun loadProduct(){
        viewModelScope.launch {
            if (dao.getAll().isEmpty()) {
                val product = listOf(
                    ProductEntity(productId = 1, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 1, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 1, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 1, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 1, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 1, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 2, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 2, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 2, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 2, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 2, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 2, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 2, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 2, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 3, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 3, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 3, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 3, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 3, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 3, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 3, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 3, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 4, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 4, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 4, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 4, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 4, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 4, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 4, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 4, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 5, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 5, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 5, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 5, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 5, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 5, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 5, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 5, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 6, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 6, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 6, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 6, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 6, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 6, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 6, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 6, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 7, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 7, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 7, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 7, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 7, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 7, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 7, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 7, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 8, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 8, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 8, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 8, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 8, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 8, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 8, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 8, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 9, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 9, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 9, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 9, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 9, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 9, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 9, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 9, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 10, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 10, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 10, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 10, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 10, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 10, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 10, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 10, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 11, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 11, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 11, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 11, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 11, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 11, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 11, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 11, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 12, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 12, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 12, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 12, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 12, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 12, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 12, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 12, image = R.drawable.step3, stepNumber = 8),
                    ProductEntity(productId = 13, image = R.drawable.step1, stepNumber = 1),
                    ProductEntity(productId = 13, image = R.drawable.step2, stepNumber = 2),
                    ProductEntity(productId = 13, image = R.drawable.step3, stepNumber = 3),
                    ProductEntity(productId = 13, image = R.drawable.step1, stepNumber = 4),
                    ProductEntity(productId = 13, image = R.drawable.step2, stepNumber = 5),
                    ProductEntity(productId = 13, image = R.drawable.step3, stepNumber = 6),
                    ProductEntity(productId = 13, image = R.drawable.step2, stepNumber = 7),
                    ProductEntity(productId = 13, image = R.drawable.step3, stepNumber = 8),
                )

                dao.insertAll(product)
            }
        }
    }
    fun getProducts(productId: Int): LiveData<List<ProductEntity>> {
        return dao.getByProduct(productId)
    }

    fun loadFavorite(itemId: Int) {
        viewModelScope.launch {
            favorite.postValue(itemDao.isFavorite(itemId))
        }
    }

    fun toggleFavorite(itemId: Int) {
        viewModelScope.launch {
            val current = itemDao.isFavorite(itemId)
            itemDao.updateFavorite(itemId, !current)
            favorite.postValue(!current)
        }
    }




}