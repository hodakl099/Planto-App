package com.example.planto_app.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planto_app.Constants
import com.example.planto_app.R
import com.example.planto_app.adapter.DiscoverPlantsAdapter
import com.example.planto_app.data.entity.DiscoveryItem
import com.example.planto_app.databinding.FragmentDiscoverPlantsBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverPlantsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class DiscoverPlantsFragment : Fragment() {

    private var _binding : FragmentDiscoverPlantsBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DiscoverPlantsAdapter

    private lateinit var listOfDiscovery : MutableList<DiscoveryItem>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDiscoverPlantsBinding.inflate(layoutInflater, container, false)


       recyclerView = binding.rvDiscovery


        setUpRecyclerView()

        adapter.setonItemClickListener {

            when (it.itemTitle) {
                "Plant Articles" -> {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(Constants.PLANT_ARTICLE_URL)
                        })
                    } catch (e: ActivityNotFoundException){
                        e.printStackTrace()
                    }
                }
                "Plant Identification" -> {
                    try {
                    } catch (e: ActivityNotFoundException){
                        e.printStackTrace()
                    }
                }
                "Plant Shop" -> {
                    Toast.makeText(requireContext(), "Plant Shop", Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }


    private fun setUpRecyclerView() {
        adapter = DiscoverPlantsAdapter(getListOfDiscoveries())
        binding.rvDiscovery.adapter = adapter
        binding.rvDiscovery.layoutManager = LinearLayoutManager(activity)
    }

    private fun getListOfDiscoveries() : MutableList<DiscoveryItem>{
        listOfDiscovery = mutableListOf(
            DiscoveryItem(
                context?.getMyDrawable(R.drawable.plant),
                "Plant Articles",
                "Search for articles about plants"
            ),
            DiscoveryItem(
                context?.getMyDrawable(R.drawable.plant),
                "Plant Identification",
                "identify plant with google Lens"
            ),
            DiscoveryItem(
                context?.getMyDrawable(R.drawable.plant),
                "Plant Shop",
                "Find nearby plant shop"
            )

        )

        return listOfDiscovery
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

// extension function to get the drawable id.

fun Context.getMyDrawable(id : Int) : Drawable?{

    return  ContextCompat.getDrawable(this, id)
}