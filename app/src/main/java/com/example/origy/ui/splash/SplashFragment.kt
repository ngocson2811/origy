package com.example.origy.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.origy.MainActivity
import com.example.origy.base.fragment.BaseFragment
import com.example.origy.databinding.FragmentSplashBinding
import com.example.origy.ui.home.HomeFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment :
    BaseFragment<FragmentSplashBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSplash()
    }

    private fun startSplash() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)

            (activity as? MainActivity)?.showBottomBar()


            navigateTo(HomeFragment())
        }
    }
}