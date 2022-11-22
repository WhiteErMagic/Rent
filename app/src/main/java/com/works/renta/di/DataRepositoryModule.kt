package com.works.renta.di

import android.content.Context
import androidx.room.Room
import com.works.renta.data.DataRepositoryImpl
import com.works.renta.data.database.AppDatabase
import com.works.renta.domain.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataRepositoryModule {

    @Provides
    @AppScope
    fun providesDataRepositoryModule(appDatabase: AppDatabase): DataRepository {
        return DataRepositoryImpl(appDatabase)
    }
}