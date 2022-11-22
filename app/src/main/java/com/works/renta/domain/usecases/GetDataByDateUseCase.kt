package com.works.renta.domain.usecases

import com.works.renta.data.database.model.DataModel
import com.works.renta.domain.DataRepository
import java.util.*

class GetDataByDateUseCase(private val repository: DataRepository) {

    fun getDataByDate(arg: Date): DataModel {
        return repository.getDataByDate(arg)
    }
}