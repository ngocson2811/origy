package com.example.origy.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.itemDetail.ItemDetailEntity
import kotlinx.coroutines.launch


class FavotiteViewModel (application: Application) : AndroidViewModel(application){
    private val dao = AppDatabase.get(application).ItemDetailDao()

    val favorites = MutableLiveData<List<ItemDetailEntity>>()

    fun loadFavorite() {
        viewModelScope.launch {
            favorites.postValue(dao.getFavorite())
        }
    }
}