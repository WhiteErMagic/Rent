package com.works.renta.domain.usecases

import com.works.renta.data.database.model.TariffsModel
import com.works.renta.domain.DataRepository
import java.util.*

class GetTariffsByDateUseCase(private val repository: DataRepository) {

    fun getTariffsByDate(arg: Date): TariffsModel {
        return repository.getTariffsByDate(arg)
    }
}