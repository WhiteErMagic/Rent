package com.works.renta.domain.usecases

import com.works.renta.data.database.model.TariffsModel
import com.works.renta.domain.DataRepository

class InsertTariffsUseCase(private val repository: DataRepository) {

    fun insertTariffs(arg: TariffsModel){
        repository.insertTariffs(arg)
    }
}