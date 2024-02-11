package com.example.instaclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instaclone.R
import com.example.instaclone.databinding.FragmentAddBinding
import com.example.instaclone.posts.PostActivity
import com.example.instaclone.posts.ReelsActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddBinding.inflate(inflater,container,false)
        binding.post.setOnClickListener{
            activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()

        }
        binding.reel.setOnClickListener{
            activity?.startActivity(Intent(requireContext(),ReelsActivity::class.java))
            activity?.finish()
        }
        return binding.root
    }

    companion object {

    }

}