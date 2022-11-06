package com.example.planto_app.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.planto_app.data.entity.Plant
import com.example.planto_app.repository.PlantRepository
import com.example.planto_app.worker.WaterReminderWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class PlantsViewModel @Inject constructor(
    val repository: PlantRepository,context: Context
) : ViewModel() {


    val getAllPlants : Flow<List<Plant>> = repository.getAllPlants()



    private val workManager = WorkManager.getInstance(context)

    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String,
    ) {

        val data = Data.Builder()
            .putString(WaterReminderWorker.nameKey,plantName)
            .build()


        val reminderWorkRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(data)
            .setInitialDelay(duration,unit)
            .build()

        workManager.enqueue(reminderWorkRequest)
    }


    private var _counter = MutableLiveData(0)
    val counter get() =
        _counter


    //add new Plant
    fun addPlant(plant: Plant) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insertPlant(plant)
        }
    }

    fun test() {
        while (_counter.value!! < 100) {
            _counter.value =+ 1
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