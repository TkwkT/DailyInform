package com.example.dailyinform.repository

import android.util.Log
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.database.ClassifyDao
import com.example.dailyinform.network.ClassifyService
import com.example.dailyinform.network.IdeaApi
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil
import com.example.dailyinform.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClassifyRepository private constructor(private val classifyDao: ClassifyDao) :BaseRepository{

    fun getClassify(type: String, callback: (List<ClassifyBean>) -> Unit) {
        val state = NetworkUtil.isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE) {
            classifyDao.getClassify(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    callback(it)
                }
                .subscribe()

        } else {
            IdeaApi.getApiService(ClassifyService::class.java)
                .getClassify(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    val list = setType(type, it.results)
                    callback(list)
                    if (it != null) {
                        addToDB(list)
                    }
                }
                .subscribe()
        }
    }

    private fun setType(type: String, list: List<ClassifyBean>): List<ClassifyBean> {
        for (c: ClassifyBean in list) {
            c.type = type
        }
        return list
    }

    private fun addToDB(list: List<ClassifyBean>) {
        runOnNewThread {
            classifyDao.insertClassifyList(list)
        }
    }


    companion object {

        @Volatile
        private var instance: ClassifyRepository? = null

        fun getInstance(classifyDao: ClassifyDao) =
            instance ?: synchronized(this) {
                instance ?: ClassifyRepository(classifyDao).also { instance = it }
            }
    }
}