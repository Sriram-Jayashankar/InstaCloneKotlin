package com.example.instaclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instaclone.R
import com.example.instaclone.adapters.MyPostRvAdapter
import com.example.instaclone.databinding.FragmentMyPostBinding
import com.example.instaclone.models.Post
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class MyPostFragment : Fragment() {
private lateinit var binding:FragmentMyPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMyPostBinding.inflate(layoutInflater,container,false)
        var postList=ArrayList<Post>()
        var adapter=MyPostRvAdapter(requireContext(),postList)
        binding.rv.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
//                var tempList= ArrayList<Post>()
                var tempList = arrayListOf<Post>()
                for(i in it.documents){
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
}