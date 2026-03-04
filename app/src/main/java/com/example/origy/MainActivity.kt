package com.example.origy

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.origy.ui.home.HomeFragment
import com.example.origy.ui.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomBar: View
    private lateinit var ivSmile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, window.decorView)

        controller.hide(WindowInsetsCompat.Type.navigationBars())

        controller.show(WindowInsetsCompat.Type.statusBars())

        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        setContentView(R.layout.activity_main)


        bottomBar = findViewById(R.id.viewBottomBar)
        ivSmile = findViewById(R.id.ivSmile)

        bottomBar.visibility = View.GONE
        ivSmile.visibility = View.GONE
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment())
                .commit()
        }



    }
    fun showBottomBar() {
        bottomBar.visibility = View.VISIBLE
        ivSmile.visibility = View.VISIBLE
    }


}