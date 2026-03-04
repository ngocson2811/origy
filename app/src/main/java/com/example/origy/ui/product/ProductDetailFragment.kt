package com.example.origy.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.origy.R
import com.example.origy.base.BaseFragment
import com.example.origy.databinding.FragmentProductDetailBinding

class ProductDetailFragment :
    BaseFragment<FragmentProductDetailBinding>() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId") ?: -1
        if (productId == -1) {
            goBack()
            return
        }

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        setupViewPager()
        setupClicks(productId)
        observeData(productId)

        setupToolbarInset(binding.topBar)
    }

    private fun setupViewPager() {
        adapter = ProductAdapter()
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.tvCount.text =
                        "${position + 1}/${adapter.itemCount}"
                }
            }
        )
    }

    private fun setupClicks(productId: Int) {

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < adapter.itemCount - 1)
                binding.viewPager.currentItem++
        }

        binding.btnPrev.setOnClickListener {
            if (binding.viewPager.currentItem > 0)
                binding.viewPager.currentItem--
        }

        binding.btnBack.setOnClickListener {
            goBack()
        }

        binding.btnReload.setOnClickListener {
            binding.viewPager.setCurrentItem(0, true)
        }

        binding.btnFavorite.setOnClickListener {
            viewModel.toggleFavorite(productId)
        }
    }

    private fun observeData(productId: Int) {

        viewModel.loadProduct()

        viewModel.getProducts(productId)
            .observe(viewLifecycleOwner) { list ->
                adapter.setData(list)
                if (list.isNotEmpty()) {
                    binding.tvCount.text = "1/${list.size}"
                }
            }

        viewModel.loadFavorite(productId)

        viewModel.favorite.observe(viewLifecycleOwner) { fav ->
            binding.btnFavorite.setImageResource(
                if (fav) R.drawable.ic_favourite_fill
                else R.drawable.ic_favourite
            )
        }
    }
}