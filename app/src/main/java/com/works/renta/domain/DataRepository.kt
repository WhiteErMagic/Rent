package com.works.renta.domain

import com.works.renta.data.database.model.DataModel
import com.works.renta.data.database.model.TariffsModel
import java.util.*

interface DataRepository {

    fun getDataByDate(arg: Date): DataModel
    fun insertData(arg: DataModel)
    fun clearDataHistory(arg: Date)

    fun getTariffsByDate(arg: Date): TariffsModel
    fun insertTariffs(arg: TariffsModel)
    fun clearTariffsHistory(arg: Date)
}