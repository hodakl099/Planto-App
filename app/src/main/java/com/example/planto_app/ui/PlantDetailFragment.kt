package com.example.planto_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.planto_app.R
import com.example.planto_app.databinding.FragmentPlantDetailBinding
import com.example.planto_app.viewmodel.PlantsViewModel


class PlantDetailFragment : Fragment() {

    private  var _binding: FragmentPlantDetailBinding? = null

    private val plantViewModel : PlantsViewModel by activityViewModels()

    private val args : PlantDetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPlantDetailBinding.inflate(layoutInflater, container,false)

        return binding.root
    }



    private fun getCurrPlantContent() = with(binding) {

        val currentPlant = plantViewModel.getTransactionById(args.currentPlant.id)

        currentPlant.observe(viewLifecycleOwner) {
            plantDetailCardView.apply {
                tvPlantName.text = it.plantName
                tvPlantType.text = it.plantType
                tvLivingRoom.text = it.plantLocation
                plantImg.setImageBitmap(it.plantImage)
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}