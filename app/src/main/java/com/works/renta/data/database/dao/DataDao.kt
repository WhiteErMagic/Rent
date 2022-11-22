package com.works.renta.data.database.dao

import androidx.room.*
import com.works.renta.data.database.model.DataModel
import java.util.*

@Dao
interface DataDao {

    @Query("select * from table_data WHERE date =:arg")
    fun getDataByDate(arg: Date): DataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(arg: DataModel)

    @Query("delete from table_data where date <= :arg")
    fun clearDataHistory(arg: Date)
}