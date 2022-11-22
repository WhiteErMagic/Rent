package com.works.renta.data

import com.works.renta.data.database.AppDatabase
import com.works.renta.data.database.model.DataModel
import com.works.renta.data.database.model.TariffsModel
import com.works.renta.domain.DataRepository
import java.util.*
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase): DataRepository {

    override fun getDataByDate(arg: Date): DataModel {
        return appDatabase.dataDao().getDataByDate(arg)
    }

    override fun insertData(arg: DataModel) {
        appDatabase.dataDao().insertData(arg)
    }

    override fun clearDataHistory(arg: Date) {
        appDatabase.dataDao().clearDataHistory(arg)
    }

    override fun getTariffsByDate(arg: Date): TariffsModel {
        return appDatabase.tariffsDao().getTariffsByDate(arg)
    }

    override fun insertTariffs(arg: TariffsModel) {
        appDatabase.tariffsDao().insertTariffs(arg)
    }

    override fun clearTariffsHistory(arg: Date) {
        appDatabase.tariffsDao().clearTariffsHistory(arg)
    }
}