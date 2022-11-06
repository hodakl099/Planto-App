package com.example.planto_app.di

import android.content.Context
import androidx.room.Room
import com.example.planto_app.data.entity.PlantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePlantDatabase(@ApplicationContext app : Context) : PlantDatabase {
        return Room.databaseBuilder(app,PlantDatabase::class.java, "plants")
            .build()
    }


    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context {
        return context
    }

}