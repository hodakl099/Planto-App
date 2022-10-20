package com.example.planto_app.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planto_app.data.entity.dao.PlantDao


@Database(
    entities = [Plant::class],
    version = 1, exportSchema = false
)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao() : PlantDao
}