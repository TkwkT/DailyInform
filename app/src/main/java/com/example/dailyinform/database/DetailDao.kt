package com.example.dailyinform.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailyinform.bean.DetailBean
import io.reactivex.Single

@Dao
interface DetailDao{

    @Query("SELECT * FROM detail WHERE classify_type = :classify")
    fun getDetailData(classify:String):Single<List<DetailBean>>

    @Insert
    fun insertDetail(detailBean: DetailBean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailList(detailList:List<DetailBean>)

}