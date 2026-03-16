package com.example.origy.ui.home.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.origy.database.AppDatabase
import com.example.origy.R
import com.example.origy.base.viewModel.BaseViewModel
import kotlinx.coroutines.launch


class CategoryViewModel(application: Application) : BaseViewModel(application) {
    private val dao = AppDatabase.get(application).categoryDao()
    val categories = dao.getAll()

    fun loadData() {
        launch {
            getCategories().forEach {
                dao.insert(it)
            }
        }
    }

    private fun getCategories(): List<CategoryEntity>{
        return listOf(
            CategoryEntity(name = "Vũ Khí", image = R.drawable.banner_vu_khi, id = 1),
            CategoryEntity(name = "Phi Cơ", image = R.drawable.banner_phi_co, id = 2),
            CategoryEntity(name = "Tàu Thuyền", image = R.drawable.banner_tau_thguyen, id = 3),
            CategoryEntity(name = "Phương Tiện Đi Lại", image = R.drawable.banner_phuong_tien, id = 4),
            CategoryEntity(name = "ROBOT", image = R.drawable.banner_robot, id = 5),
            CategoryEntity(name = "Đồ Chơi", image = R.drawable.banner_do_choi, id = 6),
            CategoryEntity(name = "Loài Vật", image = R.drawable.banner_loai_vat, id = 7),
            CategoryEntity(name = "Loài Khủng Long", image = R.drawable.banner_con_rong, id = 8),
            CategoryEntity(name = "Những Con Rồng", image = R.drawable.banner_khung_long, id = 9),
            CategoryEntity(name = "Những Con Quái Vật", image = R.drawable.banner_quai_vat, id = 10),
            CategoryEntity(name = "Nông Trại", image = R.drawable.banner_nong_trai, id = 11),
            CategoryEntity(name = "FLOWERS", image = R.drawable.banner_bong_hoa, id = 12),
        )
    }
}