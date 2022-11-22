package com.works.renta.data.database.model

import androidx.room.Entity
import java.util.*

@Entity(tableName = "table_tariffs", primaryKeys = ["date"])
data class TariffsModel(
    var date: Date?,
    var tarifElect:Float = 0.0F,
    var tarifHotwater:Float = 0.0F,
    var tarifHotwaterForColdwater:Float = 0.0F,
    var tarifSumHotwaterColdwater:Float = 0.0F,
    var tarifColdwater:Float = 0.0F
)