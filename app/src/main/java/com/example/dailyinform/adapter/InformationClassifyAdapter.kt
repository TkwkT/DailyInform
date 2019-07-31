package com.example.dailyinform.adapter

import android.content.Context
import android.util.Log
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
import com.example.dailyinform.myview.MyTextView

class InformationClassifyAdapter(private val context: Context,private val callback:(position:Int)->Unit) : RecyclerView.Adapter<BaseHolder>() {
    private val datas = ArrayList<ClassifyBean>()
    private var view:MyTextView? = null
    private var position:Int = 0

    fun freshClassifyList(list: List<ClassifyBean>) {
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
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
                setColorChange(it as MyTextView,position)
                callback(position)

            }
        }
    }

    private fun setColorChange(it:MyTextView,p: Int){
        when {
            view == null -> {
                it.isSelect = true
                view = it
                position = p
                notifyItemChanged(p)
            }
            view != it -> {
                view!!.isSelect = false
                notifyItemChanged(position)
                it.isSelect = true
                notifyItemChanged(p)
                view = it
                position = p
            }
            else -> {

            }
        }
    }

    private fun getItem(position: Int): ClassifyBean {
        return datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}