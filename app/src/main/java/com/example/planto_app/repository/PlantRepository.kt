package com.example.planto_app.repository

import com.example.planto_app.data.entity.Plant
import com.example.planto_app.data.entity.PlantDatabase
import javax.inject.Inject

class PlantRepository @Inject constructor(private val database: PlantDatabase) {


    //inset plant
    suspend fun insertPlant(plant: Plant) = database.plantDao().insertPlant(plant)

    //delete plant
    suspend fun deletePlant(plant: Plant) = database.plantDao().deletePlant(plant)

    //update plant
    suspend fun updatePlant(plant: Plant) = database.plantDao().updatePlant(plant)

    //get all transaction
    fun getAllPlants() = database.plantDao().getAllPlants()

    //delete all plants
    fun deleteAllPlants() = database.plantDao().deleteAllPlants()

    // get transaction by id
    fun getTransactionById(id : Int) = database.plantDao().getTransactionByID(id)

}