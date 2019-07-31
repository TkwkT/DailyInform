package com.example.dailyinform.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailyinform.bean.ClassifyBean
import io.reactivex.Single

@Dao
interface ClassifyDao{

    @Query("SELECT * FROM classify WHERE type = :type")
    fun getClassify(type:String):Single<List<ClassifyBean>>

    @Insert
    fun insertClassify(classifyBean: ClassifyBean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClassifyList(classifyBeanList: List<ClassifyBean>)
}