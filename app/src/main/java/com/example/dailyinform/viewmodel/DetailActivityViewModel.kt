package com.example.dailyinform.viewmodel

import android.util.Log
import android.webkit.WebView
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.repository.DetailActivityRepository
import com.example.dailyinform.utils.WebViewUtil

class DetailActivityViewModel internal constructor(private val repository: DetailActivityRepository):ViewModel(){
    val isLoading = ObservableBoolean(false)
    val progress = ObservableInt(0)

    fun setWebView(webView: WebView, url:String){
        WebViewUtil.init(webView,url,isLoading,progress)
    }
    fun insertCollectionDetail(collectionDetailBean: CollectionDetailBean){
        Log.d("aaa","aa  $collectionDetailBean")
        repository.insertCollectionDetail(collectionDetailBean)
    }
}