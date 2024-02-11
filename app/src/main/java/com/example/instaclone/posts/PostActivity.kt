package com.example.instaclone.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.R
import com.example.instaclone.Utils.POST_FOLDER
import com.example.instaclone.Utils.USER_PROFILE_FOLDER
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                if (it!= null) {
    binding.imageView2.setImageURI(uri)
                }
            }
        }
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
        binding.imageView2.setOnClickListener{
            launcher.launch("image/*")
        }

    }
}