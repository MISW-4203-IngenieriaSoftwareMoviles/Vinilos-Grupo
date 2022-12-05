package com.example.vinilos.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AssociateTrackFragmentBinding
import com.example.vinilos.databinding.DetailAlbumFragmentBinding
import com.example.vinilos.models.Album

class AssociateTrackAdapter : RecyclerView.Adapter<AssociateTrackAdapter.AssociateTrackViewHolder>(){

    var album : Album? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociateTrackViewHolder {
        val withDataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AssociateTrackViewHolder.LAYOUT,
            parent,
            false)
        return AssociateTrackViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AssociateTrackViewHolder, position: Int) {
        holder.viewDataBinding.also {
        }
        //holder.bind(album)
    }

    override fun getItemCount(): Int {
        return 1
    }


    class AssociateTrackViewHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.associate_track_fragment
        }

        /*fun bind(album: Album?) {
            Glide.with(itemView)
                .load(album?.cover?.toUri()?.buildUpon()?.scheme("https")?.build())
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.imageAlbum)
        }*/
    }

}