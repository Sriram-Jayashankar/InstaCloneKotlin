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
    inner class ViewHolder(var binding: ReelDgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reellist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reellist.get(position).profileLink).placeholder(R.drawable.userprofile)
            .into(holder.binding.profileImage)
        holder.binding.caption.setText(reellist.get(position).caption)
        holder.binding.videoView.setVideoPath(reellist.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener {
            it.isLooping = true // Set looping
            it.setVolume(1f, 1f)
            holder.binding.progressBar.visibility = View.GONE
            holder.binding.videoView.start()
        }

    }
}
//import android.content.Context
//import android.media.AudioManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.MediaController
//import android.widget.VideoView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.instaclone.R
//import com.example.instaclone.databinding.ReelDgBinding
//import com.example.instaclone.models.Reel
//import com.squareup.picasso.Picasso
//
//class ReelAdapter(private val context: Context, private val reelList: List<Reel>) :
//    RecyclerView.Adapter<ReelAdapter.ViewHolder>() {
//
//    inner class ViewHolder(private val binding: ReelDgBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(reel: Reel) {
//            Picasso.get().load(reel.profileLink).placeholder(R.drawable.userprofile)
//                .into(binding.profileImage)
//            binding.caption.text = reel.caption
//
//            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
//
//            binding.videoView.setVideoPath(reel.reelUrl)
//            binding.videoView.setOnPreparedListener { mp ->
//                mp.isLooping = true
//                mp.start()
//            }
//
//            binding.progressBar.visibility = View.GONE
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(reelList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return reelList.size
//    }
//}






//override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    val reel = reellist[position]
//
//    // Load profile image using Picasso
//    Picasso.get().load(reel.profileLink)
//        .placeholder(R.drawable.userprofile)
//        .into(holder.binding.profileImage)
//
//    // Set caption
//    holder.binding.caption.text = reel.caption
//
//    // Set video path
//    holder.binding.videoView.setVideoPath(reel.reelUrl)
//
//    // Calculate aspect ratio of the VideoView
//    holder.binding.videoView.setOnPreparedListener { mp ->
//        val videoWidth = mp.videoWidth
//        val videoHeight = mp.videoHeight
//        val videoAspectRatio = videoWidth.toFloat() / videoHeight.toFloat()
//
//        // Adjust the aspect ratio of the incoming video
//        holder.binding.videoView.setAspectRatio(videoAspectRatio)
//
//        // Hide progress bar
//        holder.binding.progressBar.visibility = View.GONE
//
//        // Start video playback
//        holder.binding.videoView.start()
//    }
//}


//override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    val reel = reellist[position]
//
//    // Load profile image using Picasso
//    Picasso.get().load(reel.profileLink)
//        .placeholder(R.drawable.userprofile)
//        .into(holder.binding.profileImage)
//
//    // Set caption
//    holder.binding.caption.text = reel.caption
//
//    // Set video path
//    holder.binding.videoView.setVideoPath(reel.reelUrl)
//
//    // Adjust video scaling to match VideoView's aspect ratio
//    holder.binding.videoView.setOnPreparedListener { mp ->
//        val videoWidth = mp.videoWidth
//        val videoHeight = mp.videoHeight
//        val videoProportion = videoWidth.toFloat() / videoHeight.toFloat()
//
//        val screenWidth = holder.binding.videoView.width
//        val screenHeight = holder.binding.videoView.height
//        val screenProportion = screenWidth.toFloat() / screenHeight.toFloat()
//
//        val lp = holder.binding.videoView.layoutParams
//
//        if (videoProportion > screenProportion) {
//            lp.width = screenWidth
//            lp.height = (screenWidth / videoProportion).toInt()
//        } else {
//            lp.width = (videoProportion * screenHeight).toInt()
//            lp.height = screenHeight
//        }
//        holder.binding.videoView.layoutParams = lp
//    }
//
//    // Start video playback
//    holder.binding.videoView.start()
//
//    // Hide progress bar
//    holder.binding.progressBar.visibility = View.GONE
//}




//.placeholder(R.drawable.userprofile)