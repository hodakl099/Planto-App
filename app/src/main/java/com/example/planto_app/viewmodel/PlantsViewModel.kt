package com.example.planto_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planto_app.data.entity.Plant
import com.example.planto_app.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlantsViewModel @Inject constructor(val repository: PlantRepository) : ViewModel() {

    val getAllPlants : Flow<List<Plant>> = repository.getAllPlants()

    //add new Plant
    fun addPlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertPlant(plant)
        }
    }

    //update plant
    fun updatePlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlant(plant)
        }
    }

    //delete plant
    fun deletePlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlant(plant)
        }
    }



    // to delete all plants in Plant table.
    fun deleteAllPlants(plant: Plant) {
        repository.deleteAllPlants()
    }


    fun getTransactionById(id: Int) : LiveData<Plant> = repository.getTransactionById(id)



}