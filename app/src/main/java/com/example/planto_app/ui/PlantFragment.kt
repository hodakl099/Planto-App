package com.example.planto_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.planto_app.R
import com.example.planto_app.adapter.PlantAdapter
import com.example.planto_app.databinding.FragmentPlantBinding


/**
 * [PlantFragment] displays a list of Plants.
 */
class PlantFragment : Fragment() {


    //Recommended by google to set ViewBinding as null.
    private  var _binding : FragmentPlantBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlantAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentPlantBinding.inflate(inflater,container,false)

        adapter = PlantAdapter()

        recyclerView = binding.rvPlants



        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}