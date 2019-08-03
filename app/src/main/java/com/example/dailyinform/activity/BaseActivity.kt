package com.example.dailyinform.activity

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyinform.R
import com.example.dailyinform.listener.NetworkConnectChangeReceiver
import com.example.dailyinform.myview.SlideFinishLayout
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil

abstract class BaseActivity : AppCompatActivity() {

    private var startInAnimationResources = 0
    private var startOutAnimationResources = 0
    private var finishInAnimationResources = 0
    private var finishOutAnimationResources = 0  //活动进出动画
    private var isInAnimated = false//是否是初次创建的resume

    private var slideFinishLayout: SlideFinishLayout? = null

    private var isRegister = false //网络广播监听器是否已经注册
    //网络监听广播接收器
    private var networkReceiver: NetworkConnectChangeReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNetworkLocalReceiver()
        //设置活动切换动画
        setInOutAnimation(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    override fun onResume() {
        super.onResume()
        initNetworkLocalReceiver()
        //设置活动进入动画
        if (startOutAnimationResources != 0) {
            if (!isInAnimated) {
                overridePendingTransition(startInAnimationResources, startOutAnimationResources)
                isInAnimated = true
            }
        }
    }

    override fun finish() {
        super.finish()
        //设置活动退出动画
        if (finishInAnimationResources != 0) {
            overridePendingTransition(finishInAnimationResources, finishOutAnimationResources)
        }
        slideFinishLayout?.detachToActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (networkReceiver != null){
            unregisterReceiver(networkReceiver)
        }
        isRegister = false
    }

    /**
     * 右滑活动返回相关↓↓↓↓↓↓↓↓
     */
    fun canSlideFinish(isCanBack: Boolean){
        if (isCanBack){
            initSlideFinish()
        }
    }

    private fun initSlideFinish() {
        slideFinishLayout = LayoutInflater.from(this).inflate(
            R.layout.slidefinish_container, null
        ) as SlideFinishLayout
        slideFinishLayout!!.attachToActivity(this)
    }

    fun addIgnoredView(v: View) {
        slideFinishLayout?.addIgnoredView(v)
    }

    fun setInOutAnimation(
        startInAnimationResources: Int, startOutAnimationResources: Int,
        finishInAnimationResources: Int, finishOutAnimationResources: Int
    ) {
        this.startInAnimationResources = startInAnimationResources
        this.startOutAnimationResources = startOutAnimationResources
        this.finishInAnimationResources = finishInAnimationResources
        this.finishOutAnimationResources = finishOutAnimationResources
    }

    private fun initNetworkLocalReceiver(){
        if (!isRegister) {
            val filter = IntentFilter()
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            networkReceiver = NetworkConnectChangeReceiver()
            registerReceiver(networkReceiver, filter)
            isRegister = true
        }
    }

    private fun listenNetwork(){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    when(NetworkUtil.isNetworkConnected(this@BaseActivity)){
                        NetworkState.WIFI -> {doOnWifi();doOnNetworkAvailable()}
                        NetworkState.MOBILE -> {doOnMobile();doOnNetworkAvailable()}
                        else -> {doOnNetworkNotAvailable()}
                    }
                }
            })
        }
    }

    open fun doOnWifi(){}

    open fun doOnMobile(){}

    open fun doOnNetworkAvailable(){}

    open fun doOnNetworkNotAvailable(){}
}
