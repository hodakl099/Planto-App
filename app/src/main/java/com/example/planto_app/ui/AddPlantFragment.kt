package com.example.planto_app.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.planto_app.MainActivity
import com.example.planto_app.databinding.FragmentAddPlantBinding


class AddPlantFragment : Fragment() {

    private var _binding : FragmentAddPlantBinding? = null


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddPlantBinding.inflate(layoutInflater,container,false)


        binding.plantImg.setOnClickListener{
//            pickImageFromGallery()
            takePhoto()
        }


        return binding.root
    }


    //function to pick an image from the gallery.
    private fun takePhoto() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.
//        resultLauncher.launch(intent)
        ActivityResultContracts.TakePicture()
    }


//    //function to pick an image from the gallery.
//    private fun pickImageFromGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        resultLauncher.launch(intent)
//    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            binding.plantImg.setImageURI(data?.data)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}