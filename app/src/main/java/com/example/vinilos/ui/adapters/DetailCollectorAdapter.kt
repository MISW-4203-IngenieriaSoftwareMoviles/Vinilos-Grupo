package com.example.vinilos.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.DetailCollectorFragmentBinding
import com.example.vinilos.models.Collector

class DetailCollectorAdapter : RecyclerView.Adapter<DetailCollectorAdapter.DetailCollectorViewHolder>(){

    var collector : Collector? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCollectorViewHolder {
        val withDataBinding: DetailCollectorFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DetailCollectorViewHolder.LAYOUT,
            parent,
            false)
        return DetailCollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DetailCollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collector
        }
        //holder.bind(collector)
    }

    override fun getItemCount(): Int {
        return 1
    }


    class DetailCollectorViewHolder(val viewDataBinding: DetailCollectorFragmentBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_collector_fragment
        }

        /*fun bind(collector: Collector?) {
            Glide.with(itemView)
                .load(collector?.email?.toUri()?.buildUpon()?.scheme("https")?.build())
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(viewDataBinding.imageAlbum)
        }*/
    }


}