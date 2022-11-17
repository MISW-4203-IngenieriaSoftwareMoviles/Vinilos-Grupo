package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.models.Album
import com.example.vinilos.R
import com.example.vinilos.databinding.DetailAlbumFragmentBinding
import com.example.vinilos.models.Performer
import com.example.vinilos.ui.AlbumFragmentDirections

class DetailAlbumAdapter: RecyclerView.Adapter<DetailAlbumAdapter.DetailAlbumViewHolder>(){

    var album : Album? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAlbumViewHolder {
        val withDataBinding: DetailAlbumFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DetailAlbumViewHolder.LAYOUT,
            parent,
            false)
        return DetailAlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DetailAlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = album
        }
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return 1
    }


    class DetailAlbumViewHolder(val viewDataBinding: DetailAlbumFragmentBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_album_fragment
        }

        fun bind(album: Album?) {
            Glide.with(itemView)
                .load(album?.cover?.toUri()?.buildUpon()?.scheme("https")?.build())
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
//                        .placeholder(R.drawable.loading_animation)
//                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.imageAlbum)
        }
    }


}