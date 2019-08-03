package com.example.dailyinform.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dailyinform.MyApplication
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.repository.DetailRepository
import com.example.dailyinform.utils.NetworkState
import com.example.dailyinform.utils.NetworkUtil

class InformationDetailViewModel internal constructor(private val repository: DetailRepository) : ViewModel() {

    fun getData(classify: String,callback: (LiveData<PagedList<DetailBean>>) -> Unit) {
        val state = NetworkUtil.isNetworkConnected(MyApplication.getContext())
        if (state == NetworkState.NONE){
            repository.getDetailFromDB(classify,callback)
        }else{
            repository.getDetailFromNet(classify,callback)
        }
    }

}