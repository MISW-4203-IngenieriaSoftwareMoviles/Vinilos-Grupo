package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.vinilos.R
import com.example.vinilos.databinding.CreateAlbumFragmentBinding

class CreateAlbumFragment : Fragment() {

    private var _binding: CreateAlbumFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = CreateAlbumFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("onViewCreated","onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        binding.btnCrear.setOnClickListener {
            findNavController().navigate(R.id.albumsCollectorFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}