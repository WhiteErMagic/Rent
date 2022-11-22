package com.works.renta.domain.entity

import com.works.renta.data.database.model.DataModel
import com.works.renta.data.database.model.TariffsModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.roundToInt

class Calculate constructor(private val dataModelPrev: DataModel?, private val dataModel: DataModel, private val tariffs: TariffsModel) {

    private var summa = 0F;
    private var elect = 0
    private var coldwater = 0
    private var hotwater = 0

    init {
        dataModelPrev?.let {
            elect = dataModel.elect - dataModelPrev.elect
            coldwater = dataModel.coldwater - dataModelPrev.coldwater
            hotwater = dataModel.hotwater - dataModelPrev.hotwater
        }
    }

    fun calculateSumma() {
        summa =
                calculateElect() + calculateColdwater() + calculateHotwater() +
                calculateVodootvedenie() + calculateHeating()
                //) * 100.0).roundToInt() / 100.0 ).toFloat()
    }

    fun getSumma(): String {
        val separator =  DecimalFormatSymbols().apply {
            groupingSeparator = ' '
            decimalSeparator = '.'
        }
        //return DecimalFormat("###,##0.00", separator).format(summa).replace(",00", "")
        return summa.let {
            if (it > 0) {
                val decimalFormat = DecimalFormat("###,##0.00", separator)
                decimalFormat.format(summa)
            } else
                "0.00"
        }
    }


    private fun calculateHeating(): Float {
        return hotwater * tariffs.tarifHotwater
    }

    private fun calculateVodootvedenie(): Float {
        return (hotwater + coldwater) * tariffs.tarifSumHotwaterColdwater
    }

    private fun calculateHotwater(): Float {
        return hotwater * tariffs.tarifHotwaterForColdwater
    }

    private fun calculateColdwater(): Float {
        return coldwater * tariffs.tarifColdwater
    }

    private fun calculateElect(): Float {
        return elect * tariffs.tarifElect
    }

//    fun result(){
//        val res = dc.elect*dc.tarifElect
//                    +dc.coldwater*dc.tarifColdwater
//                    +dc.hotwater*dc.tarifHotwaterForColdwater
//                    +(dc.hotwater+dc.coldwater)*dc.tarifSumHotwaterColdwater
//                    +dc.hotwater*dc.tarifHotwater
//    }

//    fun str():String{
//        return "Электричество: = "+dc.elect+" * "+dc.tarifElect+" = "+dc.elect*dc.tarifElect+" + \n" +
//                "Холодная вода: = "+dc.coldwater+" * "+dc.tarifColdwater+" = "+dc.coldwater*dc.tarifColdwater+"+ \n" +
//                "Холодная вода\nдля подогрева: = "+dc.hotwater+" * "+dc.tarifHotwaterForColdwater+" = "+dc.hotwater*dc.tarifHotwaterForColdwater+"+ \n" +
//                "Холодная + Горячая\n(водоотведение): = ("+dc.hotwater+" + "+dc.coldwater+")"+" * "+dc.tarifSumHotwaterColdwater+" = "+(dc.hotwater+dc.coldwater)*dc.tarifSumHotwaterColdwater+" + \n" +
//                "Подогрев воды\n(горячая вода): = "+dc.hotwater+" * "+dc.tarifHotwater+" = "+dc.hotwater*dc.tarifHotwater
//    }
}