package com.example.instaclone.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instaclone.R
import com.example.instaclone.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialtoolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialtoolbar.setOnClickListener{
            finish()
        }


    }
}