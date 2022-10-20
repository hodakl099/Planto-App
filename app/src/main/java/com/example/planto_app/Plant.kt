package com.example.planto_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "plantName")
    val plantName : String,
    @ColumnInfo(name = "plantType")
    val plantType : String,
    @ColumnInfo(name = "dailyWatering")
    val dailyWatering : Int,
    @ColumnInfo(name = "outdoorLight")
    val outdoorLight : Int,
    @ColumnInfo(name = "date")
    val date : String,
    @ColumnInfo(name = "plantLocation")
    val plantLocation : String,
    @ColumnInfo(name = "plantDescription")
    val plantDescription : String
    )
