package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.PerformerFragmentBinding
import com.example.vinilos.R
import com.example.vinilos.models.Performer
import com.example.vinilos.ui.adapters.DetailPerformerAdapter
import com.example.vinilos.viewmodels.DetailPerformerViewModel


class DetailPerformerFragment: Fragment()  {
    private var _binding: PerformerFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: DetailPerformerViewModel
    private var viewModelAdapter: DetailPerformerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PerformerFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = DetailPerformerAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.fragmentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_Performers)
        val args: DetailPerformerFragmentArgs by navArgs()
        Log.d("Args", args.type)
        viewModel = ViewModelProvider(this, DetailPerformerViewModel.Factory(activity.application, args.id, args.type)).get(
            DetailPerformerViewModel::class.java)
        viewModel.performer.observe(viewLifecycleOwner, Observer<Performer> {
            it.apply {
                viewModelAdapter!!.performer = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}