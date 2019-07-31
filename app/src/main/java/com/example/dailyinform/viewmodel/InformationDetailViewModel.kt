package com.example.dailyinform.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.repository.DetailRepository

class InformationDetailViewModel internal constructor(private val repository: DetailRepository):ViewModel(){

    val detailList = MutableLiveData<List<DetailBean>>()

    fun getData(classify:String,page:Int){
        repository.getDetail(classify,page){
            detailList.value = it
        }
    }

}