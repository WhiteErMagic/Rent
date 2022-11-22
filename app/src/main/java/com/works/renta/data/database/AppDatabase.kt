package com.works.renta.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.works.renta.data.database.dao.DataDao
import com.works.renta.data.database.dao.TariffsDao
import com.works.renta.data.database.model.DataModel
import com.works.renta.data.database.model.TariffsModel

@Database(entities = [DataModel::class, TariffsModel::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
    abstract fun tariffsDao(): TariffsDao
}