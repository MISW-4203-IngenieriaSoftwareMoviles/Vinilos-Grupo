package com.example.vinilos.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumTrackItemBinding
import com.example.vinilos.models.Album

class AlbumsCollectorAdapter : RecyclerView.Adapter<AlbumsCollectorAdapter.AlbumsCollectorViewHolder>() {

    var albums :List<Album> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsCollectorViewHolder {
        val withDataBinding: AlbumTrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsCollectorViewHolder.LAYOUT,
            parent,
            false)
        return AlbumsCollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumsCollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position])
        //holder.viewDataBinding.root.setOnClickListener {
            //val action = AlbumFragmentDirections.actionAlbumFragmentToDetailAlbumFragment(albums[position].albumId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        //}
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumsCollectorViewHolder(val viewDataBinding: AlbumTrackItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_track_item
        }

        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.imageViewAlbumtr)
        }
    }
}