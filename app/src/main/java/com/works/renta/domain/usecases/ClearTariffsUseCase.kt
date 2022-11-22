package com.works.renta.domain.usecases

import com.works.renta.domain.DataRepository
import java.util.*

class ClearTariffsUseCase(private val repository: DataRepository) {

    fun clearTariffs(arg: Date){
        repository.clearTariffsHistory(arg)
    }
}