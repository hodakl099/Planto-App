package com.example.planto_app.ui


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.planto_app.databinding.FragmentAddPlantBinding
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.example.planto_app.Constants
import com.example.planto_app.R
import com.example.planto_app.data.entity.Plant
import com.example.planto_app.viewmodel.PlantsViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import dagger.hilt.android.AndroidEntryPoint

import java.util.Calendar
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AddPlantFragment : Fragment() {

    private val plantViewModel : PlantsViewModel by activityViewModels()

    private var _binding : FragmentAddPlantBinding? = null
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2



    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddPlantBinding.inflate(layoutInflater,container,false)

        binding.ivCameraIcon.setOnClickListener {
            cameraCheckPermission()
        }
        binding.ivGalleryIcon.setOnClickListener{
            galleryCheckPermission()
        }


        binding.plantImg.setOnClickListener{
            val pictureDialog = AlertDialog.Builder(requireContext())
            pictureDialog.setTitle("Select Action")
            val pictureDialogItem = arrayOf("Select photo from Gallery",
                "Capture photo from Camera")
            pictureDialog.setItems(pictureDialogItem) { _, which ->

                when (which) {
                    0 -> gallery()
                    1 -> camera()
                }
            }

            pictureDialog.show()
        }

        binding.etPlantLocation
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.drop_down_item,
                    Constants(requireContext()).PLANT_LOCATION
                )
            )
        // set default text
        binding.etPlantLocation.setText(Constants(requireContext()).PLANT_LOCATION[0], false)


      binding.etPlantType
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.drop_down_item,
                    Constants(requireContext()).PLANT_TYPE
                )
            )

        // set Default item
        binding.etPlantType.setText(Constants(requireContext()).PLANT_TYPE[0], false)



        binding.etWateringReminder
            .setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.drop_down_item,
                    Constants(requireContext()).WATERING_SCHEDUELE
                )
            )
        // set default item
        binding.etWateringReminder.setText(Constants(requireContext()).WATERING_SCHEDUELE[0], false)


        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), { _, mYear, mMonth, mDay ->
            binding.etAdoptionDate.apply {
                setText("$mDay/${mMonth + 1}/$mYear")
                isClickable = true
                isFocusable = true
                isFocusableInTouchMode

            }
        }, year, month, day)
        dpd.show()


        binding.etAdoptionDate.setOnClickListener {
            dpd.show()
        }
        binding.addPlant.setOnClickListener {

            binding.apply {
                val (plantId,plantImage,plantName,plantType,adoptionDate,watering,plantLocation,note) = getPlantContent()

                // validate if plant content is empty or not
                when {
                    plantName.isEmpty() -> {
                        this.plantNameInput.error = "Plant name must not be empty"
                    }
                    plantType.isEmpty() -> {
                        this.etPlantType.error = "Plant type must not be empty"
                    }
                    watering.isEmpty() -> {
                        this.etWateringReminder.error = "Plant water schedule must not be empty"
                    }
                    adoptionDate.isEmpty() -> {
                        this.etAdoptionDate.error = "Plant adoption date must not be empty"
                    }
                    plantLocation.isEmpty() -> {
                        this.plantNameInput.error = "Plant location must not be empty"
                    }
                    else -> {
                        plantViewModel.addPlant(getPlantContent()).run {
                            Toast.makeText(activity,"Plant is successfully added",Toast.LENGTH_SHORT).show()

                            val directionsToPlantFragment =
                                AddPlantFragmentDirections.actionAddPlantFragmentToPlantsFragment()
                            findNavController().navigate(directionsToPlantFragment)
                        }
                    }
                }
            }
                .also {
                    when {
                        it.etWateringReminder.text.toString() == Constants.DAILY -> {
                            plantViewModel.scheduleReminder(
                                1, TimeUnit.DAYS, binding.plantName.text.toString(),
                            )
                        }
                        it.etWateringReminder.text.toString() === Constants.WEEKLY -> {
                            plantViewModel.scheduleReminder(
                                7,TimeUnit.DAYS, binding.plantName.text.toString(),
                            )
                        }
                        it.etWateringReminder.text.toString() === Constants.MONTHLY -> {
                            plantViewModel.scheduleReminder(
                                30,TimeUnit.DAYS, binding.plantName.text.toString(),
                            )
                        }
                    }
                }

        }


        return binding.root
    }

    private fun galleryCheckPermission() {

        Dexter.withContext(requireContext()).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(
                    requireContext(),
                    "You have denied the storage permission to select image",
                    Toast.LENGTH_SHORT
                ).show()
                showRotationalDialogForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?, p1: PermissionToken?) {
                showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


    private fun cameraCheckPermission() {

        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA).withListener(

                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {

                            if (report.areAllPermissionsGranted()) {
                                camera()
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?) {
                        showRotationalDialogForPermission()
                    }

                }
            ).onSameThread().check()
    }


    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                CAMERA_REQUEST_CODE -> {

                    val bitmap = data?.extras?.get("data") as Bitmap

                    //we are using coroutine image loader (coil)
                    binding.plantImg.load(bitmap) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                }

                GALLERY_REQUEST_CODE -> {

                    binding.plantImg.load(data?.data) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }

                }
            }

        }

    }



    private fun showRotationalDialogForPermission() {
        AlertDialog.Builder(requireContext())
            .setMessage("It looks like you have turned off permissions"
                    + " required for this feature. It can be enable under App settings")

            .setPositiveButton("Go TO SETTINGS") { _, _ ->

                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", activity?.packageName, null)
                    intent.data = uri
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }

            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }



    private fun getPlantContent() : Plant {
        val plantId = 0
        val plantImage =  binding.plantImg.drawable.toBitmap()
        val plantName = binding.plantName.text.toString()
        val plantType = binding.etPlantType.text.toString()
        val plantWateringSchedule = binding.etWateringReminder.text.toString()
        val adoptionDate = binding.etAdoptionDate.text.toString()
        val plantLocation = binding.etPlantLocation.text.toString()
        val note = binding.plantNoteEditText.text.toString()

        return Plant(plantId ,plantImage,plantName,plantType,adoptionDate,plantWateringSchedule,plantLocation,note)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}