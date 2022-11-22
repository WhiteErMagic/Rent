package com.works.renta.domain.usecases

import com.works.renta.domain.DataRepository
import java.util.*

class ClearDataUseCase(private val repository: DataRepository) {

    fun clearData(arg: Date){
        repository.clearDataHistory(arg)
    }
}