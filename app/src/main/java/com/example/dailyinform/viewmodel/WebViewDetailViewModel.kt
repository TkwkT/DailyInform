package com.example.dailyinform.viewmodel

import android.webkit.WebView
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.dailyinform.utils.WebViewUtil

class WebViewDetailViewModel:ViewModel(){

    val isLoading = ObservableBoolean(false)
    val progress = ObservableInt(0)

    fun setWebView(webView: WebView, url:String){
        WebViewUtil.init(webView,url,isLoading,progress)
    }


}