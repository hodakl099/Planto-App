package com.example.planto_app.data.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "plants")
data class Plant(
    @ColumnInfo(name = "plantImage")
    val plantImage : Bitmap,
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
    @ColumnInfo(name = "plantNote")
    val plantNote : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
