package com.example.planto_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.planto_app.databinding.ActivityMainBinding
import com.example.planto_app.ui.AddPlantFragment
import com.example.planto_app.ui.PlantFragment
import com.example.planto_app.ui.PlantFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        bottomNavigationView = binding.bottomNavView

        val navController = this.findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        // here to show FAB only in the plants fragment.
            // and to hide bottom navigation view in add plant fragment.
        navController.addOnDestinationChangedListener{_, destination , _ ->

            when ((destination as FragmentNavigator.Destination).className) {
                PlantFragment::class.qualifiedName -> {
                    binding.savePlantFab.visibility = View.GONE
                    binding.addPlantFab.visibility = View.VISIBLE
                    binding.bottomNavView.visibility = View.VISIBLE
                }
                AddPlantFragment::class.qualifiedName -> {
                    binding.savePlantFab.visibility = View.VISIBLE
                    binding.addPlantFab.visibility = View.GONE
                    binding.bottomNavView.visibility = View.GONE
                }
                else -> {
                    binding.addPlantFab.visibility = View.GONE
                    binding.bottomNavView.visibility = View.GONE
                }

            }
        }

         val directionsToAddPlantFragment =
            PlantFragmentDirections.actionPlantsFragmentToAddPlantFragment()

        binding.addPlantFab.setOnClickListener { view ->
           findNavController(R.id.nav_host_fragment_content_main).navigate(directionsToAddPlantFragment)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    val pickImageFromGalleryForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // handle image from gallery
        }
    }



}