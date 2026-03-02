package com.example.origy.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.ui.itemDetail.ItemDetailEntity
import kotlinx.coroutines.launch


class FavotiteViewModel (application: Application) : AndroidViewModel(application){
    private val dao = AppDatabase.get(application).ItemDetailDao()

    val favorites = MutableLiveData<List<ItemDetailEntity>>()

    val favorite = MutableLiveData<Boolean>()

    fun loadFavorite() {
        viewModelScope.launch {
            favorites.postValue(dao.getFavorite())
        }
    }

}