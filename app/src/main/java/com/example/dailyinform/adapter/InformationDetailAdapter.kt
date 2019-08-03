package com.example.dailyinform.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.R
import com.example.dailyinform.bean.DetailBean
import com.example.dailyinform.databinding.ItemDetailBinding
import com.example.dailyinform.holder.BaseHolder
import com.example.dailyinform.holder.DetailHolder
import com.example.dailyinform.holder.DetailSource

class InformationDetailAdapter(private val callback: (url: String, title: String) -> Unit) :
    RecyclerView.Adapter<BaseHolder>() {

    private val datas = ArrayList<DetailBean>()
    private var page = 1

    fun getPage(): Int {
        return page
    }

    fun setPage() {
        ++page
    }

    fun freshData(list: List<DetailBean>) {
        val position = if (datas.size != 0) {
            datas.size - 1
        } else {
            0
        }
        datas.addAll(list)
        notifyItemRangeChanged(position, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val binding = DataBindingUtil.inflate<ItemDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_detail, parent, false
        )
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (holder is DetailHolder) {
            holder.bind(DetailSource(getItem(position)))
            holder.getView().setOnClickListener {
                callback(getItem(position).url, getItem(position).title)
            }
        }
    }

    private fun getItem(position: Int): DetailBean {
        return datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }

}