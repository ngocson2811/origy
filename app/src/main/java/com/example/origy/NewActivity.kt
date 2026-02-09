package com.example.origy

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.favorite.FavotiteViewModel
import com.example.origy.itemDetail.ItemDetailAdapter
import com.google.android.material.appbar.MaterialToolbar

class NewActivity : AppCompatActivity() {

    private lateinit var viewModel: NewViewModel
    private lateinit var adapter: ItemDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new)
        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }
        val recycler = findViewById<RecyclerView>(R.id.recyclerNew)
        adapter = ItemDetailAdapter { item ->
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("productId", item.id)
            startActivity(intent)
        }
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        viewModel = ViewModelProvider(this)[NewViewModel::class.java]

        viewModel.news.observe(this){
            adapter.setData(it)
        }

        viewModel.loadNew()
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->

            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params

            insets
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNew()
    }
}