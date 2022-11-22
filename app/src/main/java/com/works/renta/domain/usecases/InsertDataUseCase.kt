package com.works.renta.domain.usecases

import com.works.renta.data.database.model.DataModel
import com.works.renta.domain.DataRepository

class InsertDataUseCase(private val repository: DataRepository) {

    fun insertData(arg: DataModel){
        repository.insertData(arg)
    }
}