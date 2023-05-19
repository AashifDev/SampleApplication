package com.example.sampleapplication.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sampleapplication.mainUi.MainActivity
import com.example.sampleapplication.R
import com.example.sampleapplication.authentication.AuthenticationActivity
import com.example.sampleapplication.session.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import java.util.Locale

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var prefManager: PrefManager
    lateinit var auth: FirebaseAuth
    lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        prefManager = PrefManager(this)
        auth = FirebaseAuth.getInstance()

        val currentUser = prefManager.getUser()
        val user = auth.currentUser


        if (!currentUser.isNullOrEmpty() && user != null ){
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this,MainActivity::class.java))
                this.finish()
            },3000)

        }else{
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this, AuthenticationActivity::class.java))
                this.finish()
            },3000)
        }

        loadLocate()
    }

    private fun setLocate(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale
        config.setLayoutDirection(locale)
        this.resources.updateConfiguration(config,this.resources?.displayMetrics)
        prefManager.setLang(lang)
    }

    private fun loadLocate(){
        val language = prefManager.getLang()
        if (language != null) {
            setLocate(language)
        }
    }
}