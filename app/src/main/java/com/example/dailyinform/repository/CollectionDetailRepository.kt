package com.example.dailyinform.repository

import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.database.CollectionDetailDao
import com.example.dailyinform.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CollectionDetailRepository private constructor(private val collectionDetailDao: CollectionDetailDao):BaseRepository {

    fun getCollectionDetail(callback: (collectionList: List<CollectionDetailBean>) -> Unit) {
        collectionDetailDao.getCollectionDetailBeanList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                callback(it)
            }
            .subscribe()
    }

    fun deleteCollectionDetail(collectionDetailBean: CollectionDetailBean) {
        runOnNewThread {
            collectionDetailDao.deleteCollectionDetailBean(collectionDetailBean)
        }
    }

    fun deleteCollectionDetailList(list: List<CollectionDetailBean>) {
        collectionDetailDao.deleteCollectionDetailBeanList(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    companion object {

        @Volatile
        private var instance: CollectionDetailRepository? = null

        fun getInstance(collectionDetailDao: CollectionDetailDao) =
            instance ?: synchronized(this) {
                instance ?: CollectionDetailRepository(collectionDetailDao).also { instance = it }
            }
    }
}