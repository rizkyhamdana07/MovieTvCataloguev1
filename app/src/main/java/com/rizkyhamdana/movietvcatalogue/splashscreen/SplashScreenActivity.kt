package com.rizkyhamdana.movietvcatalogue.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rizkyhamdana.movietvcatalogue.R
import com.rizkyhamdana.movietvcatalogue.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object{
        private const val MILLIS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, MILLIS)
    }
}