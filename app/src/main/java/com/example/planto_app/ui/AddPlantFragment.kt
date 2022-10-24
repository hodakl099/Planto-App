package com.example.planto_app.ui

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planto_app.Manifest
import com.example.planto_app.R
import com.example.planto_app.databinding.FragmentAddPlantBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder.MultiPermissionListener
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class AddPlantFragment : Fragment() {

    private var _binding : FragmentAddPlantBinding? = null
    private val CAMERA_REQUIEST_CODE = 1


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddPlantBinding.inflate(layoutInflater,container,false)

        binding.plantImg.setOnClickListener {
            permissionCameraCheck()
        }


        return binding.root
    }

    private fun permissionCameraCheck() {
        Dexter.withContext(context)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(
                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                        report?.let {
                            if (report.areAllPermissionsGranted()) {
                                openCamera()
                            }
                        }

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                        TODO("Not yet implemented")
                    }

                }
            )

    }

    private fun openCamera() {
      val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA_REQUIEST_CODE)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}