package com.example.planto_app

import android.content.Context

class Constants(context: Context) {

    companion object{
        val KEY = "currentPlant"
        val DAILY = "Daily"
        val WEEKLY = "Weekly"
        val MONTHLY = "Monthly"
    }

    val PLANT_LOCATION = arrayOf(
        context.getString(R.string.balcony),
        context.getString(R.string.hallway),
        context.getString(R.string.living_room),
        context.getString(R.string.kitchen),
        context.getString(R.string.bedroom)
    )



    val PLANT_TYPE = arrayOf(
        context.getString(R.string.climbers),
        context.getString(R.string.creepers),
        context.getString(R.string.shrubs),
        context.getString(R.string.trees),
        context.getString(R.string.herbs)
    )

    val WATERING_SCHEDUELE = arrayOf(
        context.getString(R.string.daily),
        context.getString(R.string.weekly),
        context.getString(R.string.monthly)
    )


}