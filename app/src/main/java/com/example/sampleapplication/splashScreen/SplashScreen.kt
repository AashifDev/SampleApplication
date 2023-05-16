package com.example.sampleapplication.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sampleapplication.MainActivity
import com.example.sampleapplication.R
import com.example.sampleapplication.authentication.AuthenticationActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this,AuthenticationActivity::class.java))
            this.finish()
        },3000)

    }
}