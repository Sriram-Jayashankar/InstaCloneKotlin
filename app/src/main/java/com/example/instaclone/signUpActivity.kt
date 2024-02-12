package com.example.instaclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.Utils.USER_PROFILE_FOLDER
import com.example.instaclone.Utils.uploadImage
import com.example.instaclone.databinding.ActivitySignUpBinding
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso

class signUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User//create user based on user model
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) {
                if (it == null) {

                } else {
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }//create a launcher to put picture wehen you click on addpic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val text =
            "<font color=#FF000000>Already have an account</font><font color=#248bdc>  Login?</font>"
        binding.loginrender.setText(Html.fromHtml(text))
        val db = Firebase.firestore
        user = User()
        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.signUpBtn.text="Update Profile"
                binding.loginrender.text=""
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get()
                    .addOnSuccessListener {
                        user = it.toObject<User>()!!
                        if (!user.image.isNullOrEmpty()) {
                            Picasso.get().load(user.image).into(binding.profileImage)
                        }
                        binding.namefield.editText?.setText(user.name)
                        binding.emailfield.visibility=View.INVISIBLE
                        binding.passwordfield.visibility=View.INVISIBLE

                    }
            }
        }
        binding.signUpBtn.setOnClickListener {
            if (intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    user.name = binding.namefield.editText?.text.toString();
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid)
                        .set(user)
                        .addOnSuccessListener {
                            startActivity(Intent(this@signUpActivity, HomeActivity::class.java))
                            finish()
                        }

                }
            } else {
                if (binding.namefield.editText?.text.toString().equals("") or
                    binding.emailfield.editText?.text.toString().equals("") or
                    binding.passwordfield.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        this@signUpActivity,
                        "Fill all the fields properly",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.emailfield.editText?.text.toString(),
                        binding.passwordfield.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.namefield.editText?.text.toString();
                            user.password = binding.passwordfield.editText?.text.toString();
                            user.email = binding.emailfield.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid)
                                .set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@signUpActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Log.w("why", "Error adding document", e)
                                }
                        } else {
                            Toast.makeText(
                                this@signUpActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


                }
            }
        }
        binding.AddImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.loginrender.setOnClickListener {
            startActivity(Intent(this@signUpActivity, LoginActivity::class.java))
            finish()
        }
    }
}