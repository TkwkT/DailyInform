package com.example.dailyinform.database

import androidx.room.*
import com.example.dailyinform.bean.CollectionDetailBean
import io.reactivex.Single

@Dao
interface CollectionDetailDao {

    @Query("SELECT * FROM collection_detail")
    fun getCollectionDetailBeanList():Single<List<CollectionDetailBean>>

    @Query("SELECT * FROM collection_detail WHERE url = :url")
    fun getCollectionDetailBean(url:String):Single<CollectionDetailBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollectionDetailBean(collectionDetailBean: CollectionDetailBean)

    @Delete
    fun deleteCollectionDetailBean(collectionDetailBean: CollectionDetailBean)

    @Delete
    fun deleteCollectionDetailBeanList(collectionDetailBeanList:List<CollectionDetailBean>):Single<Int>

}