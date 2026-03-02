package com.example.origy.ui.news

import android.os.Bundle
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
import com.example.origy.ui.itemDetail.ItemDetailAdapter
import com.example.origy.ui.product.ProductDetailFragment
import com.google.android.material.appbar.MaterialToolbar

class NewFragment : Fragment(R.layout.fragment_new) {

    private lateinit var viewModel: NewViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerNew)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ItemDetailAdapter { item ->

            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("productId", item.id)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        recycler.adapter = adapter


        viewModel = ViewModelProvider(this)[NewViewModel::class.java]

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loadNew()


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