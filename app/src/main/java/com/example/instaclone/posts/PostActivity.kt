package com.example.instaclone.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.HomeActivity
import com.example.instaclone.R
import com.example.instaclone.Utils.POST
import com.example.instaclone.Utils.POST_FOLDER
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.Utils.USER_PROFILE_FOLDER
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.databinding.ActivityPostBinding
import com.example.instaclone.models.Post
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.imagepost.setImageURI(uri)
                    imageUrl = url
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
        binding.materialtoolbar.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }
        binding.imagepost.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelBtn.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }
        binding.postBtn.setOnClickListener {
            val caption = binding.Caption.editText?.text.toString()
            if (imageUrl == null || caption.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
                Firebase.firestore.collection(USER_NODE).document().get()
                    .addOnSuccessListener {
                        val post: Post = Post(
                            postUrl = imageUrl!!,
                            caption = binding.Caption.editText?.text.toString(),
                            uid = Firebase.auth.currentUser!!.uid,
                            time = System.currentTimeMillis().toString()
                        )
                        Firebase.firestore.collection(POST).document().set(post)
                            .addOnSuccessListener {
                                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid)
                                    .document()
                                    .set(post)
                                    .addOnSuccessListener {
                                        startActivity(
                                            Intent(
                                                this@PostActivity,
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