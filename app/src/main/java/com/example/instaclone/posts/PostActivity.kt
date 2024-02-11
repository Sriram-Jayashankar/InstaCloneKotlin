package com.example.instaclone.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.HomeActivity
import com.example.instaclone.R
import com.example.instaclone.Utils.POST
import com.example.instaclone.Utils.POST_FOLDER
import com.example.instaclone.Utils.USER_PROFILE_FOLDER
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.databinding.ActivityPostBinding
import com.example.instaclone.models.Post
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl:String?=null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                url->
                if (url!= null) {
    binding.imagepost.setImageURI(uri)
                    imageUrl=url
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
        binding.imagepost.setOnClickListener{
            launcher.launch("image/*")
        }
        binding.postBtn.setOnClickListener{
            val post:Post=Post(imageUrl!!,binding.Caption.editText?.text.toString())
            Firebase.firestore.collection(POST).document().set(post)
                .addOnSuccessListener {  }
            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                .addOnSuccessListener {
                    startActivity(Intent(this@PostActivity,HomeActivity::class.java))
                    finish()
                }
        }

    }
}