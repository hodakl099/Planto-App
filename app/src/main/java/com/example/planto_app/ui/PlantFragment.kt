package com.example.planto_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Space
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.planto_app.R
import com.example.planto_app.adapter.PlantAdapter
import com.example.planto_app.databinding.FragmentPlantBinding
import com.example.planto_app.viewmodel.PlantsViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * [PlantFragment] displays a list of Plants.
 */
@AndroidEntryPoint
class PlantFragment : Fragment() {


    private val plantViewModel : PlantsViewModel by activityViewModels()

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

        plantViewModel.getAllPlants.observe(viewLifecycleOwner) { plants ->
            adapter.differ.submitList(plants)
        }
        setUpRecyclerView()


        return binding.root

    }
    private fun setUpRecyclerView() = lifecycleScope.launchWhenCreated {
        adapter = PlantAdapter()
        binding.rvPlants.adapter = adapter
        binding.rvPlants.layoutManager = GridLayoutManager(activity,2)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}