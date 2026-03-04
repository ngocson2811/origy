package com.example.origy.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.ui.news.NewViewModel
import com.example.origy.R
import com.example.origy.base.BaseFragment
import com.example.origy.databinding.FragmentAddMoreAppBinding
import com.example.origy.databinding.FragmentNewBinding
import com.example.origy.ui.itemDetail.ItemDetailAdapter
import com.example.origy.ui.product.ProductDetailFragment
import com.google.android.material.appbar.MaterialToolbar

class NewFragment : BaseFragment<FragmentNewBinding>() {

    private lateinit var viewModel: NewViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewBinding {
        return FragmentNewBinding.inflate(inflater,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.recyclerNew.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ItemDetailAdapter { item ->

            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("productId", item.id)
                }
            }

            navigateTo(fragment)
        }

        binding.recyclerNew.adapter = adapter


        viewModel = ViewModelProvider(this)[NewViewModel::class.java]

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loadNew()


        setupToolbarInset(binding.toolbar)
    }




}