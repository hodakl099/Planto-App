package com.example.planto_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planto_app.Constants
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

        lifecycleScope.launchWhenCreated {
            plantViewModel.getAllPlants.collect { plants ->
                adapter.differ.submitList(plants)
            }
        }

        setUpRecyclerView()

        adapter.setonItemClickListener {
            Toast.makeText(requireContext(), "Check", Toast.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putSerializable(Constants.KEY, it)
            findNavController().navigate(R.id.action_plantsFragment_to_plantDetailFragment, bundle)

        }

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