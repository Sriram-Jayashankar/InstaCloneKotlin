package com.example.instaclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instaclone.R
import com.example.instaclone.Utils.USER_NODE
import com.example.instaclone.databinding.PostRvBinding
import com.example.instaclone.models.Post
import com.example.instaclone.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PostAdaptor (var context:Context,var postList:ArrayList<Post>):RecyclerView.Adapter<PostAdaptor.MyHolder>(){
    inner class MyHolder(var binding:PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding=PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Firebase.firestore.collection(USER_NODE).document(postList[position].uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<User>()
                if (user != null) {
                    Glide.with(context)
                        .load(user.image)
                        .placeholder(R.drawable.user)
                        .into(holder.binding.profileImage) // Load profile image into ImageView
                    holder.binding.name.text = user.name
                }
            }

        Glide.with(context)
            .load(postList[position].postUrl)
            .into(holder.binding.postImage) // Load post image into ImageView
        holder.binding.time.text = postList[position].time
        holder.binding.caption.text = postList[position].caption
    }


}