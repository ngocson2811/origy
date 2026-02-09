package com.example.origy

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.origy.itemDetail.ItemDetailAdapter
import com.example.origy.itemDetail.ItemDetailViewModel
import com.example.origy.product.ProductAdapter
import com.example.origy.product.ProductEntity
import com.example.origy.product.ProductViewModel
import com.google.android.material.appbar.MaterialToolbar

class ProductDetail : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var btnFavorite: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productId = intent.getIntExtra("productId", -1)
        if (productId == -1) {
            finish()
            return
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tvCount = findViewById<TextView>(R.id.tvCount)
        viewPager.isUserInputEnabled = false
        val btnPrev = findViewById<ImageView>(R.id.btnPrev)
        val btnNext = findViewById<ImageView>(R.id.btnNext)
        btnFavorite = findViewById(R.id.btnFavorite)

        btnNext.setOnClickListener {
            if (viewPager.currentItem < adapter.itemCount - 1) {
                viewPager.currentItem++
            }
        }

        btnPrev.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem--
            }
        }
        adapter = ProductAdapter()
        viewPager.adapter = adapter

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        viewModel.loadProduct()

        viewModel.getProducts(productId)
            .observe(this) { list ->

                adapter.setData(list)

                if (list.isNotEmpty()) {
                    tvCount.text = "1/${list.size}"
                }
            }

        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                tvCount.text = "${position + 1}/${adapter.itemCount}"


            }
        })

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()

        }

        findViewById<ImageView>(R.id.btnReload).setOnClickListener {
            viewPager.setCurrentItem(0,true)
        }


        viewModel.loadFavorite(productId)
        viewModel.favorite.observe(this){ fav ->

            btnFavorite.setImageResource(
                if(fav)
                    R.drawable.ic_favourite_fill
                else
                    R.drawable.ic_favourite
            )
        }
        btnFavorite.setOnClickListener {
            viewModel.toggleFavorite(productId)
        }

        val topBar = findViewById<MaterialToolbar>(R.id.topBar)

        ViewCompat.setOnApplyWindowInsetsListener(topBar) { v, insets ->

            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params

            insets
        }

    }
}