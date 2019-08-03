package com.example.dailyinform.viewmodel

import com.example.dailyinform.bean.CollectionDetailBean

class ItemCollectDetailViewModel (collectionDetailBean: CollectionDetailBean){
    val title = collectionDetailBean.title
    val image = collectionDetailBean.cover
}