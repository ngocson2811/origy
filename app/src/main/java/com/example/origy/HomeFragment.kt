package com.example.origy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.category.CategoryAdapter
import com.example.origy.category.CategoryViewModel
import com.example.origy.itemDetail.ItemDetailFragment
import com.example.origy.itemDetail.ItemDetailViewModel
import com.google.android.material.appbar.MaterialToolbar


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var itemDetailViewModel: ItemDetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ⚠️ ViewModel dùng requireActivity() hoặc this đều được
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        itemDetailViewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategory)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter = CategoryAdapter { item ->

            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("CATEGORY_NAME", item.name)
                    putInt("categoryId", item.id)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        recycler.adapter = adapter

        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.loadData()

        // Settings
        view.findViewById<ImageView>(R.id.Settings).setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SettingFragment())
                .addToBackStack(null)
                .commit()
        }

        // Favorite
        view.findViewById<ImageView>(R.id.btnFavorite).setOnClickListener {


            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FavoriteFragment())
                .addToBackStack(null)
                .commit()
        }

        // Random product
        view.findViewById<ImageView>(R.id.btnRefresh).setOnClickListener {
            itemDetailViewModel.getRandomProduct().observe(viewLifecycleOwner) { product ->

                val fragment = ProductDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("productId", product.id)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        // New
        view.findViewById<ImageView>(R.id.btnNew).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, NewFragment())
                .addToBackStack(null)
                .commit()
        }

        // Add more apps
        view.findViewById<ImageView>(R.id.AddMoreApps).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddMoreAppFragment())
                .addToBackStack(null)
                .commit()
        }

        // Toolbar inset (status bar)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.topAppBar)
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params
            insets
        }
    }
}