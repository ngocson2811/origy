package com.example.origy.ui.addMoreApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.origy.base.BaseFragment
import com.example.origy.databinding.FragmentAddMoreAppBinding

class AddMoreAppFragment :
    BaseFragment<FragmentAddMoreAppBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddMoreAppBinding {
        return FragmentAddMoreAppBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            goBack()
        }

        setupToolbarInset(binding.toolbar)
    }
}