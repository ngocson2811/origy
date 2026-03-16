package com.example.origy.ui.itemDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.ui.product.ProductDetailFragment
import com.example.origy.R
import com.example.origy.base.fragment.BaseFragment
import com.example.origy.databinding.FragmentItemDetailBinding
import com.google.android.material.appbar.MaterialToolbar

class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>() {

    private lateinit var viewModel: ItemDetailViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItemDetailBinding {
        return FragmentItemDetailBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.getString("CATEGORY_NAME")
        val categoryId = arguments?.getInt("categoryId") ?: 0

        binding.tvTitle.text = categoryName

        binding.ivBack.setOnClickListener {
            goBack()
        }
        binding.recyclerItemDetail.layoutManager = GridLayoutManager(requireContext(),2)


        adapter = ItemDetailAdapter { product ->
            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("productId", product.id)
                }
            }

            navigateTo(fragment)
        }
        binding.recyclerItemDetail.adapter = adapter

        viewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]

        viewModel.getItems(categoryId).observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.seedProduct()

        setupToolbarInset(binding.toolbar)
    }


}