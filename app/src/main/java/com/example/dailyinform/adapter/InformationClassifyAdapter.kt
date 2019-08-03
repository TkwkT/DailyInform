package com.example.dailyinform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.R
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.databinding.ItemClassifyBinding
import com.example.dailyinform.holder.BaseHolder
import com.example.dailyinform.holder.ClassifyHolder
import com.example.dailyinform.holder.ClassifySource

class InformationClassifyAdapter(private val callback: (position: Int) -> Unit) : RecyclerView.Adapter<BaseHolder>() {
    private var currentPosition = 0
    private val classifyList = ArrayList<ClassifyBean>()

    fun freshClassifyList(list: List<ClassifyBean>) {
        classifyList.addAll(list)
        list[0].isSelect = true
        notifyDataSetChanged()
    }

    fun setCurrentPosition(position: Int) {
        setItemChange(position)
    }

    private fun setItemChange(position: Int) {
        if (position != currentPosition) {
            classifyList[currentPosition].isSelect = false
            notifyItemChanged(currentPosition, 1)
            classifyList[position].isSelect = true
            notifyItemChanged(position, 1)
            currentPosition = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val binding = DataBindingUtil.inflate<ItemClassifyBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_classify, parent, false
        )
        return ClassifyHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (holder is ClassifyHolder) {
            holder.bind(ClassifySource(getItem(position)))
            holder.getView().setOnClickListener {
                setItemChange(position)
                callback(position)
            }
        }
    }



    private fun getItem(position: Int): ClassifyBean {
        return classifyList[position]
    }

    override fun getItemCount(): Int {
        return classifyList.size
    }

}
