package com.example.dailyinform.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


object NetworkUtil {

    fun isNetworkConnected(context: Context): NetworkState {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return NetworkState.NONE
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkState.WIFI
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                -> NetworkState.MOBILE
                else -> NetworkState.NONE
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return NetworkState.NONE
            return when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> NetworkState.WIFI
                ConnectivityManager.TYPE_MOBILE_DUN -> NetworkState.MOBILE
                else -> NetworkState.NONE
            }
        }
    }
}

enum class NetworkState(val state: Int) {
    NONE(0),
    MOBILE(1),
    WIFI(2)
}