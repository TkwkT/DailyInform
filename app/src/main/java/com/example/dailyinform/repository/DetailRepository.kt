package com.example.dailyinform.repository

import android.util.Log
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.database.DetailDao
import com.example.dailyinform.network.DetailService
import com.example.dailyinform.network.IdeaApi
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil
import com.google.gson.JsonParseException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.SocketException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

class DetailRepository private constructor(private val detailDao: DetailDao) {

    fun getDetail(classify: String,page:Int,callback:(List<DetailBean>)-> Unit){
        val state = NetworkUtil.isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE) {
            detailDao.getDetailData(classify)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    callback(it)
                }
                .subscribe()
        } else {
            IdeaApi.getApiService(DetailService::class.java)
                .getDetail(classify,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    callback(it.results)
                }
                .subscribe()
//            runOnNewThread {
//                detailDao.insertDetailList(detailList)
//            }
        }
    }

    companion object {

        @Volatile
        private var instance: DetailRepository? = null

        fun getInstance(detailDao: DetailDao) =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(detailDao).also { instance = it }
            }
    }

}