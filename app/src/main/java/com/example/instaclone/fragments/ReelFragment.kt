package com.example.instaclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instaclone.R
import com.example.instaclone.Utils.REEL
import com.example.instaclone.adapters.ReelAdaptor
import com.example.instaclone.databinding.FragmentReelBinding
import com.example.instaclone.models.Reel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class ReelFragment : Fragment() {
    private lateinit var binding:FragmentReelBinding
    lateinit var adapter:ReelAdaptor
    var reelList=ArrayList<Reel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentReelBinding.inflate(inflater,container,false)
        adapter= ReelAdaptor(requireContext(),reelList)
        binding.ViewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get()
            .addOnSuccessListener {
                var tempList=ArrayList<Reel>()
                reelList.clear()
                for(i in it.documents){
                    var reel:Reel=i.toObject<Reel>()!!
                    tempList.add(reel)
                }
                reelList.addAll(tempList)
                reelList.reverse()
                adapter.notifyDataSetChanged()
            }
        return binding.root
    }

    companion object {

    }
}