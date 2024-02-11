package com.example.instaclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instaclone.R
import com.example.instaclone.Utils.REEL
import com.example.instaclone.adapters.MyPostRvAdapter
import com.example.instaclone.adapters.MyReelAdapter
import com.example.instaclone.databinding.FragmentMyReelsBinding
import com.example.instaclone.models.Post
import com.example.instaclone.models.Reel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class MyReelsFragment : Fragment() {

private lateinit var binding:FragmentMyReelsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMyReelsBinding.inflate(inflater,container,false)
        var reelList=ArrayList<Reel>()
        var adapter= MyReelAdapter(requireContext(), reelList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).get()
            .addOnSuccessListener {
//                var tempList= ArrayList<Post>()
                var tempList = arrayListOf<Reel>()
                for(i in it.documents){
                    var reel: Reel =i.toObject<Reel>()!!
                    tempList.add(reel)
                }
                reelList.addAll(tempList)
                adapter.notifyDataSetChanged()
            }
        return binding.root
    }

    companion object {

    }
}