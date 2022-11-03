package com.example.planto_app.data.entity

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "plantImage")
    val plantImage : Bitmap,
    @ColumnInfo(name = "plantName")
    val plantName : String,
    @ColumnInfo(name = "plantType")
    val plantType : String,
    @ColumnInfo(name = "plantAdoptionDate")
    val AdoptionDate : String,
    @ColumnInfo(name = "dailyWatering")
    val dailyWatering : Int,
    @ColumnInfo(name = "outdoorLight")
    val outdoorLight : Int,
    @ColumnInfo(name = "plantLocation")
    val plantLocation : String,
    @ColumnInfo(name = "plantNote")
    val plantNote : String
    ) : Parcelable