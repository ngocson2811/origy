package com.example.origy.ui.product

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.origy.R
import com.google.android.material.appbar.MaterialToolbar

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private lateinit var btnFavorite: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId") ?: -1
        if (productId == -1) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            return
        }

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tvCount = view.findViewById<TextView>(R.id.tvCount)
        val btnPrev = view.findViewById<ImageView>(R.id.btnPrev)
        val btnNext = view.findViewById<ImageView>(R.id.btnNext)
        btnFavorite = view.findViewById(R.id.btnFavorite)

        adapter = ProductAdapter()
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false

        btnNext.setOnClickListener {
            if (viewPager.currentItem < adapter.itemCount - 1)
                viewPager.currentItem++
        }

        btnPrev.setOnClickListener {
            if (viewPager.currentItem > 0)
                viewPager.currentItem--
        }

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        viewModel.loadProduct()
        viewModel.getProducts(productId).observe(viewLifecycleOwner) {
            adapter.setData(it)
            if (it.isNotEmpty()) {
                tvCount.text = "1/${it.size}"
            }
        }

        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tvCount.text = "${position + 1}/${adapter.itemCount}"
            }
        })

        view.findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        view.findViewById<ImageView>(R.id.btnReload).setOnClickListener {
            viewPager.setCurrentItem(0, true)
        }

        viewModel.loadFavorite(productId)
        viewModel.favorite.observe(viewLifecycleOwner) { fav ->
            btnFavorite.setImageResource(
                if (fav) R.drawable.ic_favourite_fill
                else R.drawable.ic_favourite
            )
        }

        btnFavorite.setOnClickListener {
            viewModel.toggleFavorite(productId)
        }

        val topBar = view.findViewById<MaterialToolbar>(R.id.topBar)

        ViewCompat.setOnApplyWindowInsetsListener(topBar) { v, insets ->

            val statusBarHeight =
                insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = statusBarHeight
            v.layoutParams = params

            insets
        }
    }
}