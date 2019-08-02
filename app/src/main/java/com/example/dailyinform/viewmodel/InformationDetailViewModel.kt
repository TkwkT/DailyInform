package com.example.dailyinform.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.repository.DetailRepository

class InformationDetailViewModel internal constructor(private val repository: DetailRepository) : ViewModel() {

    fun getData(classify: String,callback: (LiveData<PagedList<DetailBean>>) -> Unit) {
        repository.getDetail(classify) {
            callback(it)
        }
    }

}