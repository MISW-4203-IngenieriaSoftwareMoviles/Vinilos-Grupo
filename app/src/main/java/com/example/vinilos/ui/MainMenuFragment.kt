package com.example.vinilos.ui

import android.os.Bundle

import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentFirstPageBinding
import com.example.vinilos.databinding.FragmentMainMenuBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment() {


    private var _binding: FragmentMainMenuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("onViewCreated","onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        binding.btnAlbumes.setOnClickListener {
            findNavController().navigate(R.id.main_menu_to_album_fragment)
        }

        binding.btnColeccionistas.setOnClickListener {
            findNavController().navigate(R.id.main_menu_to_collector_fragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}