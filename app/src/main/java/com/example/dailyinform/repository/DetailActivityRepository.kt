package com.example.dailyinform.repository

import android.util.Log
import androidx.room.EmptyResultSetException
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.database.CollectionDetailDao
import com.example.dailyinform.database.DetailDao
import com.example.dailyinform.utils.ToastUtil
import com.example.dailyinform.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailActivityRepository private constructor(
    private val collectionDetailDao: CollectionDetailDao
) : BaseRepository {

    fun insertCollectionDetail(collectionDetailBean: CollectionDetailBean) {
        Log.d("aaa", collectionDetailBean.url)
        collectionDetailDao.getCollectionDetailBean(collectionDetailBean.url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                ToastUtil.setToast("已收藏",MyApplication.getContext())
                Log.d("aaa", "insert: $it")
            }
            .doOnError{
                ToastUtil.setToast("收藏成功",MyApplication.getContext())
                if (it is EmptyResultSetException){
                    insert(collectionDetailBean)
                }
            }
            .subscribe()
    }


    private fun insert(collectionDetailBean: CollectionDetailBean) {
        runOnNewThread {
            collectionDetailDao.insertCollectionDetailBean(collectionDetailBean)
        }
    }


    companion object {

        @Volatile
        private var instance: DetailActivityRepository? = null

        fun getInstance(collectionDetailDao: CollectionDetailDao) =
            instance ?: synchronized(this) {
                instance ?: DetailActivityRepository(collectionDetailDao).also { instance = it }
            }
    }
}
