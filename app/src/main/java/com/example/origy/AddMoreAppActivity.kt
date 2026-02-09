package com.example.origy

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class AddMoreAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_more_app)

        findViewById<ImageView>(R.id.ivBack).setOnClickListener {
            finish()
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->

            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = topInset
            v.layoutParams = params

            insets
        }
    }
}