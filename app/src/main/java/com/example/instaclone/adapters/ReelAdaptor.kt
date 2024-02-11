package com.example.instaclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instaclone.R
import com.example.instaclone.databinding.ReelDgBinding
import com.example.instaclone.models.Reel
import com.squareup.picasso.Picasso

class ReelAdaptor(var context:Context,var reellist:ArrayList<Reel>): RecyclerView.Adapter<ReelAdaptor.ViewHolder>() {
     inner class ViewHolder(var binding:ReelDgBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=ReelDgBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reellist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reellist.get(position).profileLink).placeholder(R.drawable.userprofile).into(holder.binding.profileImage)
        holder.binding.caption.setText(reellist.get(position).caption)
        holder.binding.videoView.setVideoPath(reellist.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener{
            holder.binding.progressBar.visibility= View.GONE
            holder.binding.videoView.start()
        }

    }
}

//.placeholder(R.drawable.userprofile)