package com.example.origy.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.R
import kotlinx.coroutines.launch


class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.get(application).categoryDao()
    val categories = MutableLiveData<List<CategoryEntity>>()

    fun loadData(){
        viewModelScope.launch {
            if (dao.getCount() == 0) {
                val listCategory = listOf(
                    CategoryEntity(name = "Vũ Khí", image = R.drawable.image_category),
                    CategoryEntity(name = "Phi Cơ", image = R.drawable.image_category),
                    CategoryEntity(name = "Tàu Thuyền", image = R.drawable.image_category),
                    CategoryEntity(name = "Phương Tiện Đi Lại", image = R.drawable.image_category),
                    CategoryEntity(name = "ROBOT", image = R.drawable.image_category),
                    CategoryEntity(name = "Đồ Chơi", image = R.drawable.image_category),
                    CategoryEntity(name = "Loài Vật", image = R.drawable.image_category),
                    CategoryEntity(name = "Loài Khủng Long", image = R.drawable.image_category),
                    CategoryEntity(name = "Những Con Rồng", image = R.drawable.image_category),
                    CategoryEntity(name = "Những Con Quái Vật", image = R.drawable.image_category),
                    CategoryEntity(name = "Nông Trại", image = R.drawable.image_category),
                    CategoryEntity(name = "FLOWERS", image = R.drawable.image_category),
                )

                dao.insertAll(listCategory)
            }

            categories.postValue(dao.getAll())
        }
    }
}