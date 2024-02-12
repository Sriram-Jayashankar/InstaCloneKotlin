package com.example.instaclone.posts

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.HomeActivity
import com.example.instaclone.R
import com.example.instaclone.Utils.POST
import com.example.instaclone.Utils.POST_FOLDER
import com.example.instaclone.Utils.REEL
import com.example.instaclone.Utils.REEL_FOLDER
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.Utils.uploadVideo
import com.example.instaclone.databinding.ActivityReelsBinding
import com.example.instaclone.models.Post
import com.example.instaclone.models.Reel
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class ReelsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private  var VideoUrl:String?=null
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
        progressDialog= ProgressDialog(this)

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
            val caption = binding.Caption.editText?.text.toString()
            if (VideoUrl == null || caption.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    var user:User=it.toObject<User>()!!
                    val reel: Reel = Reel(VideoUrl!!,binding.Caption.editText?.text.toString(),user.image!!)
                    Firebase.firestore.collection(REEL).document().set(reel)
                        .addOnSuccessListener {
                            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL)
                                .document().set(reel)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@ReelsActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                        }
                }

        }
        }
    }
}