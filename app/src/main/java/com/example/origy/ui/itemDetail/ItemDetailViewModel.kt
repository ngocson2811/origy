package com.example.origy.ui.itemDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.R
import kotlinx.coroutines.launch


class ItemDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.get(application).ItemDetailDao()
    fun getItems(categoryId: Int): LiveData<List<ItemDetailEntity>> {
        return dao.getByCategory(categoryId)
    }
    fun seedProduct() {
        viewModelScope.launch {

            val seedIds = seedItems.map { it.id }

            seedItems.forEach { item ->
                val exists = dao.exists(item.id) > 0

                if (!exists) {
                    dao.insert(item)
                } else {
                    dao.updateSeedData(
                        item.id,
                        item.image,
                        item.isNew,
                        item.name
                    )
                }
            }

            dao.deleteItemsNotIn(seedIds)
        }
    }


    private val seedItems = listOf(
        ItemDetailEntity(categoryId = 1, name = "Ninja Star", image = R.drawable.ninja_star_33, isNew = true,id = 1,),
        ItemDetailEntity(categoryId = 1, name = "Kunai", image = R.drawable.kunai_19,id = 2,),
        ItemDetailEntity(categoryId = 2, name = "Airplane", image = R.drawable.airplane_27,isNew = true,id = 3,),
        ItemDetailEntity(categoryId = 3, name = "Boat", image = R.drawable.boat_22,id = 4,),
        ItemDetailEntity(categoryId = 4, name = "Car", image = R.drawable.car_30,isNew = true,id = 5,),
        ItemDetailEntity(categoryId = 5, name = "Robot", image = R.drawable.robot_52,id = 6,),
        ItemDetailEntity(categoryId = 6, name = "Frog", image = R.drawable.frog_9,id = 7,),
        ItemDetailEntity(categoryId = 7, name = "Bird", image = R.drawable.bird_11,id = 8,),
        ItemDetailEntity(categoryId = 8, name = "Dinosaurs", image = R.drawable.dinosaurs_28,id = 9,),
        ItemDetailEntity(categoryId = 9, name = "Dragon", image = R.drawable.dragon_30,id = 10,),
        ItemDetailEntity(categoryId = 10, name = "Monster", image = R.drawable.monster_54,id = 11,),
        ItemDetailEntity(categoryId = 11, name = "Horse", image = R.drawable.horse,id = 12,),
        ItemDetailEntity(categoryId = 12, name = "Flower", image = R.drawable.flower,id = 13,),
    )



    fun getRandomProduct(): LiveData<ItemDetailEntity> = liveData {
        emit(dao.getRandomProduct())
    }




}