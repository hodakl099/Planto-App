package com.example.planto_app

import android.content.Context

class Constants(context: Context) {

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

}