package com.example.instaclone.posts

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.HomeActivity
import com.example.instaclone.R
import com.example.instaclone.Utils.POST
import com.example.instaclone.Utils.POST_FOLDER
import com.example.instaclone.Utils.REEL
import com.example.instaclone.Utils.REEL_FOLDER
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.Utils.uploadVideo
import com.example.instaclone.databinding.ActivityReelsBinding
import com.example.instaclone.models.Post
import com.example.instaclone.models.Reel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class ReelsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var VideoUrl:String
    lateinit var progressDialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER,progressDialog) {
                    url->
                if (url!= null) {
                    VideoUrl=url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)

        setSupportActionBar(binding.materialtoolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialtoolbar.setOnClickListener{
            startActivity(Intent(this@ReelsActivity,HomeActivity::class.java))
            finish()
        }
        binding.reelpost.setOnClickListener{
            launcher.launch("video/*")
        }
        binding.cancelBtn.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postBtn.setOnClickListener{
            val reel: Reel = Reel(VideoUrl!!,binding.Caption.editText?.text.toString())
            Firebase.firestore.collection(REEL).document().set(reel)
                .addOnSuccessListener {  }
            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).document().set(reel)
                .addOnSuccessListener {
                    startActivity(Intent(this@ReelsActivity,HomeActivity::class.java))
                    finish()
                }
        }
    }
}