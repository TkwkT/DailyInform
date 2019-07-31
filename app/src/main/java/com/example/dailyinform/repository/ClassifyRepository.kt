package com.example.dailyinform.repository

import android.util.Log
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.database.ClassifyDao
import com.example.dailyinform.network.ClassifyService
import com.example.dailyinform.network.IdeaApi
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClassifyRepository private constructor(private val classifyDao: ClassifyDao) {

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
                    callback(it.results)
                    Log.d("aaa",it.toString())
                }
                .subscribe()
//            runOnNewThread {
//                classifyDao.insertClassifyList(classifyList)
//            }
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