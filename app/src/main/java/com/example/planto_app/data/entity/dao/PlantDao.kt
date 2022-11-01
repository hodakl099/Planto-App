package com.example.planto_app.data.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.planto_app.data.entity.Plant
import kotlinx.coroutines.flow.Flow


@Dao
interface PlantDao {

    // to insert new plant to Plants table.
    @Insert(onConflict = REPLACE)
    suspend fun insertPlant(plant: Plant)

    //to update plant in Plants table.
    @Update
    suspend fun updatePlant(plant: Plant)

    //used to delete plant from Plants table.
    @Delete
    suspend fun deletePlant(plant: Plant)

    // get all plants in Plants table.
    @Query("SELECT * FROM plants")
     fun getAllPlants() : LiveData<List<Plant>>

     //Delete all plants in Plants Table.
    @Query("Delete From plants")
    fun deleteAllPlants()









}