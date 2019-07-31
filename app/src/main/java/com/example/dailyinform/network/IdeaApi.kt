package com.example.dailyinform.network

import com.example.dailyinform.utils.RetrofitUtil

object IdeaApi {

    fun <T>getApiService(cls:Class<T>,baseUrl:String = " http://gank.io/api/"): T {
        val retrofit = RetrofitUtil.getRetrofitBuilder(baseUrl).build()
        return retrofit.create(cls)
    }
}