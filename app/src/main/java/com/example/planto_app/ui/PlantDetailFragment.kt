package com.example.planto_app.ui



import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.planto_app.databinding.FragmentPlantDetailBinding
import com.example.planto_app.viewmodel.PlantsViewModel


private const val TAG = "PlantDetailFragment"


class PlantDetailFragment : Fragment() {

    private  var _binding: FragmentPlantDetailBinding? = null

    private val plantViewModel : PlantsViewModel by activityViewModels()

    private val args : PlantDetailFragmentArgs by navArgs()

    private var counter = 0

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentPlantDetailBinding.inflate(layoutInflater, container,false)

        // get current plant information.
        getCurrPlantContent()

        // to setup watering progress bar.
        setUpProgressWatering()

        return binding.root
    }



    private fun getCurrPlantContent() = with(binding) {

        val currentPlant = plantViewModel.getTransactionById(args.currentPlant.id)

        currentPlant.observe(viewLifecycleOwner) {
            plantDetailCardView.apply {
                tvPlantName.text = it.plantName
                tvPlantType.text = it.plantType
                tvOutDoorDays.text = it.outdoorLight.toString()
                tvWateringDays.text = it.dailyWatering.toString()
                tvAdoptionDate.text = it.AdoptionDate
                plantImg.setImageBitmap(it.plantImage)
            }

        }

    }

    // To setUp progress for watering plants.
    private fun setUpProgressWatering() {
        binding.plantDetailCardView.btnWater.setOnClickListener{ btn ->


            while (counter  < 100) {
                counter++

                Log.i(TAG,"counter is $counter")

                binding.plantDetailCardView.progressBar.isVisible = true

//                update progress bar and animate to watered check.
                binding.plantDetailCardView.progressBar.progress = counter


                when (counter) {
                    100 -> {
                        binding.plantDetailCardView.btnWater.text = counter.toString()
                    }
                }

            }



        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}