package com.example.instaclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.instaclone.databinding.ActivitySignUpBinding
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class signUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val db = Firebase.firestore
user= User()
        binding.signUpBtn.setOnClickListener{
            if(binding.namefield.editText?.text.toString().equals("")or
                binding.emailfield.editText?.text.toString().equals("") or
                binding.passwordfield.editText?.text.toString().equals(""))
            {
                Toast.makeText(this@signUpActivity,"Fill all the fields properly",Toast.LENGTH_SHORT).show()
            }
            else
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailfield.editText?.text.toString(),
                    binding.passwordfield.editText?.text.toString()
                ).addOnCompleteListener { result->
                    if (result.isSuccessful) {
                        user.name=binding.namefield.editText?.text.toString();
                        user.password=binding.passwordfield.editText?.text.toString();
                        user.email=binding.emailfield.editText?.text.toString()
                        Firebase.firestore.collection("User").document(Firebase.auth.currentUser!!.uid)
                            .set(user)
                            .addOnSuccessListener { Toast.makeText(this@signUpActivity, "SignUp successful", Toast.LENGTH_SHORT).show() }
                            .addOnFailureListener { e ->
                                    Log.w("why", "Error adding document", e)
                            }
                    }else{
                        Toast.makeText(this@signUpActivity, result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }
    }
}