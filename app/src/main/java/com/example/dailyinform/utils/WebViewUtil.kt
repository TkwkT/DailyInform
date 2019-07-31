package com.example.dailyinform.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

object WebViewUtil{

    @SuppressLint("JavascriptInterface")
    fun init(webView:WebView,url: String,isLoading:ObservableBoolean,progress:ObservableInt){

        val webViewClient = object :WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                isLoading.set(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isLoading.set(false)
            }
        }

        val webChromeClient = object :WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progress.set(newProgress)
            }
        }

        webView.webViewClient = webViewClient
        webView.webChromeClient = webChromeClient

        webView.loadUrl(url)
        webView.addJavascriptInterface(this,"android")


        val seting = webView.settings

        //允许使用js
        seting.javaScriptEnabled = true

        //不使用缓存
        seting.cacheMode = WebSettings.LOAD_NO_CACHE

        //屏幕缩放
        seting.setSupportZoom(true)
        seting.builtInZoomControls = true

    }

}
