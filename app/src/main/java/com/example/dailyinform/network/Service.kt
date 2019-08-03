package com.example.dailyinform.network

import com.example.dailyinform.bean.BaseBean
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.bean.DetailBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassifyService{
    @GET("xiandu/category/{type}")
    fun getClassify(@Path("type")type:String):Observable<BaseBean<ClassifyBean>>
}

interface DetailService{
    @GET("xiandu/data/id/{classify}/count/{count}/page/{page}")
    fun getDetail(@Path("classify")classify:String, @Path("count")count:Int, @Path("page")page:Int):Observable<BaseBean<DetailBean>>
}

