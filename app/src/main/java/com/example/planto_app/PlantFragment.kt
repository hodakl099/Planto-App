package com.example.planto_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planto_app.databinding.FragmentPlantBinding


class PlantFragment : Fragment() {


    //Recommended by google to set ViewBinding as null.
    private  var _binding : FragmentPlantBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentPlantBinding.inflate(inflater,container,false)


        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}