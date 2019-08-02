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
    @GET("xiandu/data/id/{classify}/count/30/page/{page}")
    fun getDetail(@Path("classify")classify:String,@Path("page")page:Int):Observable<BaseBean<DetailBean>>
}

