package com.example.planto_app.ui



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.planto_app.Constants
import com.example.planto_app.R
import com.example.planto_app.databinding.FragmentPlantDetailBinding
import com.example.planto_app.viewmodel.PlantsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlantDetailFragment : Fragment() {

    private  var _binding: FragmentPlantDetailBinding? = null

    private val plantViewModel : PlantsViewModel by activityViewModels()

    private val args : PlantDetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentPlantDetailBinding.inflate(layoutInflater, container,false)

        // get current plant information.
        getCurrPlantContent()

        val test =  args.currentPlant.id
        Log.i("TEST","$test")

        return binding.root
    }



    @SuppressLint("SetTextI18n")
    private fun getCurrPlantContent() = with(binding) {

        val currentPlant = plantViewModel.getTransactionById(args.currentPlant.id)

        currentPlant.observe(viewLifecycleOwner) {plant ->
            plantDetailCardView.apply {
                tvPlantName.text = plant.plantName
                tvPlantType.text = plant.plantType
                when (plant.dailyWatering) {
                    Constants.DAILY -> {
                        tvWateringDays.text = "1"
                    }
                    Constants.WEEKLY -> {
                        tvWateringDays.text = "7"
                    }
                    Constants.MONTHLY -> {
                        tvWateringDays.text = "30"
                    }
                }

                tvAdoptionDate.text = plant.AdoptionDate
                plantImg.setImageBitmap(plant.plantImage)
            }


            binding.plantDetailCardView.ivEditIcon.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable(Constants.KEY, plant)

                findNavController().navigate(R.id.action_plantDetailFragment_to_editPlantFragment,bundle)

            }

        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}