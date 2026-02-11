package com.example.origy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.origy.favorite.FavotiteViewModel
import com.example.origy.itemDetail.ItemDetailAdapter
import com.google.android.material.appbar.MaterialToolbar

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var viewModel: FavotiteViewModel
    private lateinit var adapter: ItemDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back
        view.findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // RecyclerView
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerFavorite)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ItemDetailAdapter { item ->
            // 👉 CHUYỂN SANG ProductDetailFragment
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

        // ViewModel
        viewModel = ViewModelProvider(this)[FavotiteViewModel::class.java]

        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loadFavorite()

        // Status bar inset
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
