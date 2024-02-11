//package com.example.instaclone.adapters
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.instaclone.databinding.MyPostRvDesignBinding
//import com.example.instaclone.models.Post
//import com.example.instaclone.models.Reel
//import com.squareup.picasso.Picasso
//
//
//class MyReelAdapter(var context:Context,var reelList:ArrayList<Reel>):
//RecyclerView.Adapter<MyReelAdapter.ViewHolder>() {
//    inner class ViewHolder(var binding:MyPostRvDesignBinding):RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        var binding=MyPostRvDesignBinding.inflate(LayoutInflater.from(context),parent,false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return reelList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(context).load(reelList.get(position).reelUrl)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(holder.binding.postImage)
//    }
//}


// MyReelAdapter.kt
package com.example.instaclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.instaclone.databinding.MyReelItemBinding
import com.example.instaclone.models.Reel

class MyReelAdapter(
    private val context: Context,
    private val reelList: ArrayList<Reel>
) : RecyclerView.Adapter<MyReelAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MyReelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reel: Reel) {
            Glide.with(context)
                .load(reel.reelUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.reelImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MyReelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reelList[position])
    }

    override fun getItemCount(): Int {
        return reelList.size
    }
}
