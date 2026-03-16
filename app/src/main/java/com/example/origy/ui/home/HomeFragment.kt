package com.example.origy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.origy.base.fragment.BaseFragment
import com.example.origy.databinding.FragmentHomeBinding
import com.example.origy.ui.addMoreApp.AddMoreAppFragment
import com.example.origy.ui.home.category.CategoryAdapter
import com.example.origy.ui.home.category.CategoryViewModel
import com.example.origy.ui.favorite.FavoriteFragment
import com.example.origy.ui.itemDetail.ItemDetailFragment
import com.example.origy.ui.itemDetail.ItemDetailViewModel
import com.example.origy.ui.news.NewFragment
import com.example.origy.ui.product.ProductDetailFragment
import com.example.origy.ui.setting.SettingFragment
import kotlinx.coroutines.launch

class HomeFragment :
    BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private lateinit var itemDetailViewModel: ItemDetailViewModel

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        itemDetailViewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]

        setupRecycler()
        setupClickEvents()
        observeData()

        setupToolbarInset(binding.topAppBar)
    }

    private fun setupRecycler() {
        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(requireContext())

        adapter = CategoryAdapter { item ->
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("CATEGORY_NAME", item.name)
                    putInt("categoryId", item.id)
                }
            }
            navigateTo(fragment)
        }

        binding.recyclerCategory.adapter = adapter
    }

    private fun observeData() {
        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.loadData()
    }

    private fun setupClickEvents() {

        binding.Settings.setOnClickListener {
            navigateTo(SettingFragment())
        }

        binding.btnFavorite.setOnClickListener {
            navigateTo(FavoriteFragment())
        }

        binding.btnNew.setOnClickListener {
            navigateTo(NewFragment())
        }

        binding.AddMoreApps.setOnClickListener {
            navigateTo(AddMoreAppFragment())
        }

        binding.btnRefresh.setOnClickListener {

            lifecycleScope.launch {

                val product = itemDetailViewModel.getRandomProduct()

                val fragment = ProductDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("productId", product.id)
                    }
                }

                navigateTo(fragment)
            }
        }

        binding.Share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Tải app Origami tại đây: "
                )
            }

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Chia sẻ ứng dụng qua"
                )
            )
        }
    }
}