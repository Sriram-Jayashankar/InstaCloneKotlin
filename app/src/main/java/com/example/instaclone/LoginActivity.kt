package com.example.instaclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instaclone.databinding.ActivityLoginBinding
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginUpBtn.setOnClickListener {
            if (binding.emailfield.editText?.text.toString().equals("") or
                binding.passwordfield.editText?.text.toString().equals("")
            ) {
                Toast.makeText(
                    this@LoginActivity,
                    "Enter Login Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var user = User(
                    binding.emailfield.editText?.text.toString(),
                    binding.passwordfield.editText?.text.toString()
                )
                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        binding.signUprender.setOnClickListener {
            startActivity(Intent(this@LoginActivity,signUpActivity::class.java))
            finish()
        }
    }
}