package com.example.dailyinform.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.repository.ClassifyRepository

class InformationClassifyViewModel internal constructor(private val repository: ClassifyRepository) : ViewModel() {

    val classifyList = MutableLiveData<List<ClassifyBean>>()
    private val arrayList = ArrayList<ClassifyBean>()

    fun getData(type: String) {
        repository.getClassify(type) {
            arrayList.addAll(it)
            classifyList.value = arrayList
        }
    }

}