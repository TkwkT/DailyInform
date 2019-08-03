package com.example.dailyinform.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.repository.CollectionDetailRepository

class CollectionDetailViewModel internal constructor(private val repository: CollectionDetailRepository) : ViewModel() {

    val hasData = MutableLiveData<Boolean>()
    val collectionDetailList = MutableLiveData<List<CollectionDetailBean>>()

    fun deleteCollectionDetailList(list: List<CollectionDetailBean>) {
        repository.deleteCollectionDetailList(list)
    }

    fun deleteCollectionDdtail(collectionDetailBean: CollectionDetailBean) {
        repository.deleteCollectionDetail(collectionDetailBean)
    }

    fun getCollectionDetailBeanList() {
        repository.getCollectionDetail {
            Log.d("aaa",it.toString())

            if (it.isNotEmpty()) {
                hasData.value = true
                collectionDetailList.value = it
            } else {
                hasData.value = false
            }
        }
    }

}