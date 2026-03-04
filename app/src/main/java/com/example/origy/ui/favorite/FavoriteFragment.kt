package com.example.origy.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.origy.base.BaseFragment
import com.example.origy.databinding.FragmentFavoriteBinding
import com.example.origy.ui.itemDetail.ItemDetailAdapter
import com.example.origy.ui.product.ProductDetailFragment

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding>() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        setupRecycler()
        setupClickEvents()
        observeData()

        setupToolbarInset(binding.toolbar)
    }

    private fun setupRecycler() {
        binding.recyclerFavorite.layoutManager =
            GridLayoutManager(requireContext(), 2)

        adapter = ItemDetailAdapter { item ->
            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("productId", item.id)
                }
            }
            navigateTo(fragment)
        }

        binding.recyclerFavorite.adapter = adapter
    }

    private fun setupClickEvents() {
        binding.ivBack.setOnClickListener {
            goBack()
        }
    }

    private fun observeData() {
        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loadFavorite()
    }
}