package com.example.instaclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instaclone.R
import com.example.instaclone.Utils.POST
import com.example.instaclone.adapters.PostAdaptor
import com.example.instaclone.databinding.FragmentHomeBinding
import com.example.instaclone.models.Post
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding
private var postList=ArrayList<Post>()
    private lateinit var adapter:PostAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        adapter=PostAdaptor(requireContext(),postList)
        binding.postRv.layoutManager=LinearLayoutManager(requireContext())
        binding.postRv.adapter=adapter
        setHasOptionsMenu(true)
        (requireActivity()as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        Firebase.firestore.collection(POST).get()
            .addOnSuccessListener {
                var tempList=ArrayList<Post>()
                postList.clear()
                for(i in it.documents)
                {
                    var post:Post=i.toObject<Post>()!!
                    tempList.add(post)
                }
                postList.addAll(tempList)
                adapter.notifyDataSetChanged()
            }



        return binding.root
    }

    companion object {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}