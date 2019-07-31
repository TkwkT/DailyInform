package com.example.dailyinform.holder

import android.util.Log
import android.widget.LinearLayout
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.databinding.ItemDetailBinding
import com.example.dailyinform.viewmodel.ItemDetailViewModel

class DetailHolder(private val itemDetailBinding:ItemDetailBinding) :BaseHolder(itemDetailBinding){

    override fun <T> bind(t: T) {
        if (t is DetailSource){
            itemDetailBinding.viewmodel = ItemDetailViewModel(t.detailBean)
        }
    }

    fun getView():LinearLayout{
        return itemDetailBinding.detailLayout
    }
}
data class DetailSource(
    val detailBean: DetailBean
)