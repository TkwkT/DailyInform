package com.example.dailyinform.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.database.DetailDao
import com.example.dailyinform.network.PagingDetailDataSourceFactory
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil

class DetailRepository private constructor(private val detailDao: DetailDao) {

    fun getDetail(classify: String,callback:(LiveData<PagedList<DetailBean>>) -> Unit){
        val state = NetworkUtil.isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE) {
            val detailList = LivePagedListBuilder(detailDao.getDetailData(classify),PagedList
                .Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(ENABLE_PLACEHOLDER).build()).build()
            callback(detailList)

        } else {
            val detailList = LivePagedListBuilder(PagingDetailDataSourceFactory(classify),PagedList
                .Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(ENABLE_PLACEHOLDER).build()).build()
            Log.d("aaa","re"+detailList.value.toString())
            callback(detailList)
        }
    }

    companion object {

        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDER = true

        @Volatile
        private var instance: DetailRepository? = null

        fun getInstance(detailDao: DetailDao) =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(detailDao).also { instance = it }
            }
    }

}