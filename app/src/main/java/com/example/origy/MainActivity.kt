package com.example.origy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.category.CategoryAdapter
import com.example.origy.category.CategoryViewModel
import com.example.origy.itemDetail.ItemDetailViewModel
import com.example.origy.product.ProductViewModel
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter : CategoryAdapter

    private lateinit var itemDetailViewModel: ItemDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        val recycler = findViewById<RecyclerView>(R.id.recyclerCategory)
        val settings = findViewById<ImageView>(R.id.Settings)
        settings.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        val btnFavorite = findViewById<ImageView>(R.id.btnFavorite)
        btnFavorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        recycler.layoutManager = LinearLayoutManager(this)

        adapter = CategoryAdapter { item ->

            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("CATEGORY_NAME", item.name)
            intent.putExtra("categoryId", item.id)
            startActivity(intent)
        }
        recycler.adapter = adapter
        viewModel.categories.observe(this){
            adapter.setData(it)
        }
        viewModel.loadData()

        itemDetailViewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]
        findViewById<ImageView>(R.id.btnRefresh).setOnClickListener {

            itemDetailViewModel.getRandomProduct().observe(this) { product ->


                val intent = Intent(this, ProductDetail::class.java)
                intent.putExtra("productId", product.id)
                startActivity(intent)
            }
        }

        findViewById<ImageView>(R.id.btnNew).setOnClickListener {

            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)

        }
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->

            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params

            insets
        }

        findViewById<ImageView>(R.id.AddMoreApps).setOnClickListener {
            val intent = Intent(this, AddMoreAppActivity::class.java)
            startActivity(intent)
        }

    }


}