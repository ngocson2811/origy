package com.example.origy.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.origy.base.fragment.BaseFragment
import com.example.origy.databinding.FragmentSettingBinding
import com.example.origy.ui.addMoreApp.AddMoreAppFragment

class SettingFragment :
    BaseFragment<FragmentSettingBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            goBack()
        }
        binding.viewWebsite.setOnClickListener {
            navigateTo(AddMoreAppFragment())
        }

        binding.viewAppReview.setOnClickListener {
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
        setupToolbarInset(binding.toolbar)
    }
}