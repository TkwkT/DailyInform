package com.example.dailyinform.holder

import android.widget.FrameLayout
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.databinding.ItemCollectDetailBinding
import com.example.dailyinform.viewmodel.ItemCollectDetailViewModel

class CollectHolder(private val itemCollectDetailBinding: ItemCollectDetailBinding) : BaseHolder(itemCollectDetailBinding) {
    override fun <T> bind(t: T) {
        if (t is CollectSource){
            itemCollectDetailBinding.viewmodel = ItemCollectDetailViewModel(t.collectDetailBean)
        }
    }

    fun getView():FrameLayout{
        return itemCollectDetailBinding.collectFrame
    }
}

data class CollectSource(
    val collectDetailBean: CollectionDetailBean
)