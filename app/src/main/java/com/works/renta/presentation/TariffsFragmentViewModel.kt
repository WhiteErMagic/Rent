package com.works.renta.presentation

import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.works.renta.data.database.model.TariffsModel
import com.works.renta.domain.DataRepository
import com.works.renta.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class TariffsFragmentViewModel @Inject constructor(private val repository: DataRepository): ViewModel() {

    //Use cases
    private val getTariffsByDateUseCase: GetTariffsByDateUseCase = GetTariffsByDateUseCase(repository)
    private val insertTariffsUseCase: InsertTariffsUseCase = InsertTariffsUseCase(repository)

    //Parameters
    private var periodDate: Long = 0

    //LiveData
    private val _tariffsOfPeriod = MutableLiveData<TariffsModel>()
    val tariffsOfPeriod:LiveData<TariffsModel> = _tariffsOfPeriod
    private val _periodStr = MutableLiveData<String>()
    val periodStr: LiveData<String> = _periodStr

    fun getTariffsByUseCase(){
        viewModelScope.launch(Dispatchers.IO) {
            var tariffsModel = getTariffsByDateUseCase.getTariffsByDate(Date(periodDate))
            if (tariffsModel == null)
                tariffsModel = TariffsModel(Date(periodDate))
            _tariffsOfPeriod.postValue(tariffsModel)
        }
    }

    fun setPeriodDate(arg:Long){
        periodDate = arg
        _periodStr.value = DateFormat.format("MMMM yyyy",
            periodDate).toString()
        getTariffsByUseCase()
    }

    fun saveTariffs(arg: TariffsModel){
        viewModelScope.launch(Dispatchers.IO) {
            insertTariffsUseCase.insertTariffs(arg)
        }
    }
}