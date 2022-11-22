package com.works.renta.data.database.dao

import androidx.room.*
import com.works.renta.data.database.model.TariffsModel
import java.util.*

@Dao
interface TariffsDao {

    @Query("select * from table_tariffs WHERE date <=:arg ORDER BY date desc LIMIT 1")
    fun getTariffsByDate(arg: Date): TariffsModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTariffs(arg: TariffsModel)

    @Query("delete from table_tariffs where date < :arg")
    fun clearTariffsHistory(arg: Date)
}