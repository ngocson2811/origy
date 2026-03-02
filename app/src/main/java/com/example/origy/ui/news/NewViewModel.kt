package com.example.origy.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.ui.itemDetail.ItemDetailEntity
import kotlinx.coroutines.launch

class NewViewModel (application: Application) : AndroidViewModel(application){
    private val dao = AppDatabase.Companion.get(application).ItemDetailDao()

    val news = MutableLiveData<List<ItemDetailEntity>>()

    fun loadNew() {
        viewModelScope.launch {
            news.postValue(dao.getNewItems())
        }
    }
}