package com.mek.internshipproject.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mek.internshipproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplash()
        setContentView(R.layout.activity_main)

    }
    private fun initSplash() {
        Thread.sleep(3000)
        installSplashScreen()
    }
}