package com.example.dailyinform.network

import androidx.paging.PageKeyedDataSource
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.database.AppDatabase
import com.example.dailyinform.database.DetailDao
import com.example.dailyinform.utils.ExecuteOnceObserver
import com.example.dailyinform.utils.runOnNewThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PagingDetailDataSource(private val classsify: String) : PageKeyedDataSource<Int, DetailBean>() {
    private lateinit var detailDao: DetailDao
    private lateinit var detailService: DetailService
    private var page = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DetailBean>) {
        detailService = IdeaApi.getApiService(DetailService::class.java)
        detailService.getDetail(classsify, 15, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                if (it != null) {
                    val list = setClassify(classsify, it.results)
                    callback.onResult(list, 0, page++)
                    addToDataBase(list)
                }
            }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DetailBean>) {
        detailService = IdeaApi.getApiService(DetailService::class.java)
        detailService.getDetail(classsify, 15, params.key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                if (it != null) {
                    val list = setClassify(classsify, it.results)
                    callback.onResult(list, page++)
                    addToDataBase(list)
                }
            }))
    }

    private fun addToDataBase(list: List<DetailBean>) {
        detailDao = AppDatabase.getInstance(MyApplication.getContext()).detailDao()
        runOnNewThread {
            detailDao.insertDetailList(list)
        }
    }

    private fun setClassify(classify: String, list: List<DetailBean>): List<DetailBean> {
        for (d: DetailBean in list) {
            d.classifyType = classify
        }
        return list
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DetailBean>) {

    }

}