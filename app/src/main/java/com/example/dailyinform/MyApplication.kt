package com.example.dailyinform

import android.app.Application
import android.content.Context
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import io.reactivex.plugins.RxJavaPlugins

class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return
//        }
//        LeakCanary.install(this)
        RxJavaPlugins.setErrorHandler {
            val message = it.message?:""
            Log.d("aaa",message)
        }
        context = applicationContext
    }

    companion object{
        private lateinit var context:Context

        fun getContext():Context{
            return context
        }
    }

}