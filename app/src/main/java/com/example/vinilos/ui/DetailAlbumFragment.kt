package com.example.vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.AlbumsFragmentBinding
import com.example.vinyls_jetpack_application.R
import com.example.vinyls_jetpack_application.databinding.AlbumsFragmentBinding
import com.example.vinyls_jetpack_application.models.Albums
import com.example.vinyls_jetpack_application.ui.adapters.AlbumssAdapter
import com.example.vinyls_jetpack_application.viewmodels.AlbumsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailAlbumFragment : Fragment() {
    private var _binding: AlbumsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumsViewModel
    private var viewModelAdapter: AlbumssAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlbumsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumssAdapter()
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
        activity.actionBar?.title = getString(R.string.title_Albumss)
        viewModel = ViewModelProvider(this, AlbumsViewModel.Factory(activity.application)).get(AlbumsViewModel::class.java)
        viewModel.Albumss.observe(viewLifecycleOwner, Observer<List<Albums>> {
            it.apply {
                viewModelAdapter!!.Albumss = this
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