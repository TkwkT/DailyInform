package com.example.dailyinform.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.BaseBean
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.database.AppDatabase
import com.example.dailyinform.utils.ExecuteOnceObserver
import com.example.dailyinform.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PagingDetailDataSource(private val classsify: String) : PageKeyedDataSource<Int, DetailBean>() {

    private lateinit var detailService: DetailService
    private var page = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DetailBean>) {
        detailService = IdeaApi.getApiService(DetailService::class.java)
        detailService.getDetail(classsify, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
//                addToDataBase(it.results)
                Log.d("aaa","sou in $it")
                callback.onResult(it.results, 0, page++)
            }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DetailBean>) {
        detailService = IdeaApi.getApiService(DetailService::class.java)
        detailService.getDetail(classsify, params.key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
//                addToDataBase(it.results)
                Log.d("aaa","sou af $it")
                callback.onResult(it.results, page++)
            }))
    }

    private fun addToDataBase(list:List<DetailBean>){
        runOnNewThread {
            AppDatabase.getInstance(MyApplication.getContext()).detailDao().insertDetailList(list)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DetailBean>) {

    }

}