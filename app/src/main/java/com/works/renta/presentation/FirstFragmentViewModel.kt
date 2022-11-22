package com.works.renta.presentation

import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.works.renta.data.database.model.DataModel
import com.works.renta.data.database.model.TariffsModel
import com.works.renta.domain.DataRepository
import com.works.renta.domain.entity.Calculate
import com.works.renta.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(private val repository: DataRepository): ViewModel() {

    //Use cases
    private val getByDateUseCase: GetDataByDateUseCase = GetDataByDateUseCase(repository)
    private val insertDataUseCase: InsertDataUseCase = InsertDataUseCase(repository)
    private val clearDataUseCase: ClearDataUseCase = ClearDataUseCase(repository)
    private val clearTariffsUseCase: ClearTariffsUseCase = ClearTariffsUseCase(repository)
    private val getTariffsByDateUseCase: GetTariffsByDateUseCase = GetTariffsByDateUseCase(repository)

    //Parameters
    private var dataModelPrev: DataModel? = null
    private lateinit var periodDate: Date
    private var tariffs: TariffsModel? = null

    //LivaData
    private val _periodStr = MutableLiveData<String>()
    val periodStr:LiveData<String> = _periodStr
    private val _summa = MutableLiveData<String>()
    val summa:LiveData<String> = _summa
    private val _dataOfPeriod = MutableLiveData<DataModel>()
    val dataOfPeriod:LiveData<DataModel> = _dataOfPeriod

    init {
        val c: Calendar = Calendar.getInstance()
        var year: Int = c.get(Calendar.YEAR)
        var month: Int = c.get(Calendar.MONTH)
        month++
        val tempDate = SimpleDateFormat("yyyy-MM-dd")
            .parse(year.toString()+"-"+month.toString()+"-1").time
        setPeriodDate(tempDate)
    }

    fun getByDateUseCase(){
        val calendar = Calendar.getInstance()
        calendar.time = periodDate
        calendar.add(Calendar.MONTH, -1)
        viewModelScope.launch(Dispatchers.IO) {
            dataModelPrev = getByDateUseCase.getDataByDate(Date(calendar.timeInMillis))
            var dataModel = getByDateUseCase.getDataByDate(periodDate)
            tariffs = getTariffsByDateUseCase.getTariffsByDate(periodDate)
            if(dataModel == null)
                dataModel = DataModel(periodDate)
            if(tariffs == null)
                tariffs = TariffsModel(periodDate)
            _dataOfPeriod.postValue(dataModel)
        }
    }

    fun setPeriodDate(date:Long){
        periodDate = Date(date)
        _periodStr.value = DateFormat.format("MMM yyyy", periodDate).toString()
            .let{it.replaceFirstChar(Char::titlecase)}
        getByDateUseCase()
        _summa.value = "0.00"
    }

    fun getPeriodDate():Long{
        return periodDate.time
    }

    fun saveData(arg: DataModel){
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.insertData(arg)
        }
    }

    fun doClear() {
        viewModelScope.launch(Dispatchers.IO) {
            clearDataUseCase.clearData(periodDate)
            clearTariffsUseCase.clearTariffs(periodDate)
            getByDateUseCase()
        }
    }

    fun doCalculate(dataModel: DataModel) {
        saveData(dataModel)
        val calculate = Calculate(dataModelPrev ,dataModel, tariffs!!)

        calculate.calculateSumma()
        _summa.value = calculate.getSumma()
    }

}