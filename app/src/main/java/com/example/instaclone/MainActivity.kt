package com.example.instaclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor=Color.TRANSPARENT
        // intent to go to next activity after 3 sec
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,signUpActivity::class.java))
        },3000)

    }
}