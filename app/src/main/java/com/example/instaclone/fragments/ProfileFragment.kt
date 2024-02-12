
package com.example.instaclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instaclone.R
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.adapters.ViewPagerAdapter
import com.example.instaclone.databinding.FragmentProfileBinding
import com.example.instaclone.models.User
import com.example.instaclone.signUpActivity
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater,container,false)
        binding.editProfile.setOnClickListener {
            val intent=Intent(activity,signUpActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()
        }
        binding.logout.setOnClickListener {
            // Log out the user
            FirebaseAuth.getInstance().signOut()
            // Navigate back to the sign-up activity
            startActivity(Intent(activity, signUpActivity::class.java))
            activity?.finish()
        }
        viewPagerAdapter= ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragments(MyPostFragment(),"My Post")
        viewPagerAdapter.addFragments(MyReelsFragment(),"My Reels")
        binding.viewPager.adapter=viewPagerAdapter
        binding.TabLayout.setupWithViewPager(binding.viewPager)

        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user:User=it.toObject<User>()!!
                binding.name.text="Name : "+user.name
                binding.bio.text="Email : "+user.email
                if(!user.image.isNullOrEmpty())
                {
                    Picasso.get().load(user.image).into(binding.profileImage)
                }
            }
    }
}
