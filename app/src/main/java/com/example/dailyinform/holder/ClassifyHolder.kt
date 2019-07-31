package com.example.dailyinform.holder

import android.view.View
import android.widget.TextView
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.databinding.ItemClassifyBinding
import com.example.dailyinform.myview.MyTextView
import com.example.dailyinform.viewmodel.ItemClassifyViewModel

class ClassifyHolder(private val itemClassifyBinding:ItemClassifyBinding) :BaseHolder(itemClassifyBinding){

    override fun <T> bind(t: T) {
        if (t is ClassifySource){
            itemClassifyBinding.viewmodel = ItemClassifyViewModel(t.classifyBean)
        }
    }

    fun getView(): MyTextView {
        return itemClassifyBinding.tvClassify
    }

}

data class ClassifySource(
    val classifyBean:ClassifyBean
)