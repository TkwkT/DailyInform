package com.example.dailyinform.holder

import android.os.Build
import android.view.View
import android.widget.TextView
import com.example.dailyinform.R
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.databinding.ItemClassifyBinding
import com.example.dailyinform.myview.MyTextView
import com.example.dailyinform.viewmodel.ItemClassifyViewModel

class ClassifyHolder(private val itemClassifyBinding: ItemClassifyBinding) : BaseHolder(itemClassifyBinding) {

    override fun <T> bind(t: T) {
        if (t is ClassifySource) {
            itemClassifyBinding.viewmodel = ItemClassifyViewModel(t.classifyBean)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (t.classifyBean.isSelect) {
                    itemClassifyBinding.tvClassify.setTextColor(context.getColor(R.color.item_text_select))
                }else{
                    itemClassifyBinding.tvClassify.setTextColor(context.getColor(R.color.item_text_unselect))
                }
            }

        }
    }

    fun getView(): TextView {
        return itemClassifyBinding.tvClassify
    }

}

data class ClassifySource(
    val classifyBean: ClassifyBean
)