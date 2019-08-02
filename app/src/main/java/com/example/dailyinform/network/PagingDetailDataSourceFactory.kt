package com.example.dailyinform.network

import android.util.Log
import androidx.paging.DataSource
import com.example.dailyinform.bean.DetailBean

class PagingDetailDataSourceFactory(private val classify: String) : DataSource.Factory<Int, DetailBean>() {
    override fun create(): DataSource<Int, DetailBean>{
        Log.d("aaa","create")
        return PagingDetailDataSource(classify)
    }
}