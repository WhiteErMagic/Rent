package com.works.renta.data.database.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "table_data", primaryKeys = ["date"])
data class DataModel(
    var date: Date?,
    var elect:Int = 0,
    var hotwater:Int = 0,
    var coldwater:Int = 0
)