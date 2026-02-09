package com.example.origy

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.itemDetail.ItemDetailAdapter
import com.example.origy.itemDetail.ItemDetailViewModel
import com.example.origy.product.ProductViewModel
import com.google.android.material.appbar.MaterialToolbar

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemDetailViewModel
    private lateinit var adapter: ItemDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }
        val categoryName = intent.getStringExtra("CATEGORY_NAME")
        val categoryId = intent.getIntExtra("categoryId", 0)
        findViewById<TextView>(R.id.tvTitle).text = categoryName
        val recycler = findViewById<RecyclerView>(R.id.recyclerItemDetail)
        recycler.layoutManager = GridLayoutManager(this, 2)
        adapter = ItemDetailAdapter { product ->
            val intent = Intent(this, ProductDetail::class.java)
            Intent(this, FavoriteActivity::class.java)

            intent.putExtra("productId", product.id)
            startActivity(intent)

        }

        recycler.adapter = adapter
        viewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]

        viewModel.getItems(categoryId).observe(this){
            adapter.setData(it)
        }



        viewModel.seedProduct()

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->

            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params

            insets
        }
    }

}