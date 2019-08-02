package com.example.dailyinform.viewmodel

import androidx.databinding.ObservableField
import com.example.dailyinform.bean.DetailBean

class ItemDetailViewModel(detailBean: DetailBean) {
    val title = ObservableField(detailBean.title)
}