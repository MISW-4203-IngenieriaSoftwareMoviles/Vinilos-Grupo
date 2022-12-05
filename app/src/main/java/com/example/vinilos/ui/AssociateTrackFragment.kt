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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.AssociateTrackFragmentBinding
import com.example.vinilos.R
import com.example.vinilos.models.Track
import com.example.vinilos.ui.adapters.AlbumAdapter
import com.example.vinilos.viewmodels.AlbumViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AssociateTrackFragment : Fragment() {
    private var _binding: AssociateTrackFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AssociateTrackFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("onViewCreated","onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        binding.btnAssociateTrack.setOnClickListener {
            findNavController().navigate(R.id.albumsCollectorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}