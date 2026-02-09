package com.example.origy

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.max = 100
        progressBar.progress = 0

        lifecycleScope.launch {
            for (i in 0..100) {
                progressBar.progress = i
                delay(30)
            }
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }
}