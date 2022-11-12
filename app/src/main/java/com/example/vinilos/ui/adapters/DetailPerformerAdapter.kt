package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.models.Performer
import com.example.vinilos.R
import com.example.vinilos.databinding.DetailPerformerFragmentBinding
import com.example.vinilos.ui.PerformerFragmentDirections

class DetailPerformerAdapter: RecyclerView.Adapter<DetailPerformerAdapter.DetailPerformerViewHolder>() {

    var peformer : Performer? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPerformerViewHolder {
        val withDataBinding: DetailPerformerFragmentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DetailPerformerViewHolder.LAYOUT,
            parent,
            false)
        return DetailPerformerViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DetailPerformerViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = peformer
        }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class DetailPerformerViewHolder(val viewDataBinding: DetailPerformerFragmentBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.detail_performer_fragment
        }
    }
}