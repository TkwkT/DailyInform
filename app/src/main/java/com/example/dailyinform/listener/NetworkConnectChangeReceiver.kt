package com.example.dailyinform.listener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil
import com.example.dailyinform.utils.ToastUtil


class NetworkConnectChangeReceiver : BroadcastReceiver() {

    private val TAG = "NetworkChangeReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }
        if (intent.action === ConnectivityManager.CONNECTIVITY_ACTION) {
            when (NetworkUtil.isNetworkConnected(context)) {
                NetworkState.MOBILE -> ToastUtil.setToast("移动网络已连接", context)
                NetworkState.WIFI -> ToastUtil.setToast("WIFI网络已连接", context)
                else -> ToastUtil.setToast("网络连接已断开", context)
            }
        }
    }

}
