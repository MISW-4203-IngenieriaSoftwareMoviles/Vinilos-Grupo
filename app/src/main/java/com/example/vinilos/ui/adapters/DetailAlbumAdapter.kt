package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.models.Album
import com.example.vinilos.R
import com.example.vinilos.databinding.DetailAlbumFragmentBinding
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
    }


}