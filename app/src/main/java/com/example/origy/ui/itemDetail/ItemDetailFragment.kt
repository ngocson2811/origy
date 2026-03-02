package com.example.origy.ui.itemDetail

import android.os.Bundle
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
import com.google.android.material.appbar.MaterialToolbar

class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    private lateinit var viewModel: ItemDetailViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.getString("CATEGORY_NAME")
        val categoryId = arguments?.getInt("categoryId") ?: 0

        view.findViewById<TextView>(R.id.tvTitle).text = categoryName

        view.findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerItemDetail)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ItemDetailAdapter { product ->
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
        recycler.adapter = adapter

        viewModel = ViewModelProvider(this)[ItemDetailViewModel::class.java]

        viewModel.getItems(categoryId).observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.seedProduct()

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params
            insets
        }
    }
}