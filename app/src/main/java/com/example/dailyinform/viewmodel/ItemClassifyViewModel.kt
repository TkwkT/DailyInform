package com.example.dailyinform.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.dailyinform.bean.ClassifyBean

class ItemClassifyViewModel(classifyBean: ClassifyBean) {
    val title = ObservableField(classifyBean.title)
    val isselect = ObservableBoolean(classifyBean.isSelect)
}