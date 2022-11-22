package com.works.renta.di

import android.content.Context
import androidx.room.Room
import com.works.renta.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Provides
    @AppScope
    fun RoomModule(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "renta-db").build()
    }
}