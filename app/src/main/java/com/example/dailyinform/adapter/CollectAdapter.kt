package com.example.dailyinform.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.R
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.databinding.ItemCollectDetailBinding
import com.example.dailyinform.holder.BaseHolder
import com.example.dailyinform.holder.CollectHolder
import com.example.dailyinform.holder.CollectSource

class CollectAdapter(private val callback: (url:String,title:String,cover:String) -> Unit) : RecyclerView.Adapter<BaseHolder>() {
    private val mAsync = AsyncListDiffer(this, diffCallback)

    fun submitData(list: List<CollectionDetailBean>) {
        val a = ArrayList<CollectionDetailBean>()
        a.addAll(list)
        Log.d("aaa","" + a)
        mAsync.submitList(a)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        Log.d("aaa","1")
        val binding = DataBindingUtil.inflate<ItemCollectDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_collect_detail, parent, false
        )
        return CollectHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        Log.d("aaa","2")
        if (holder is CollectHolder) {
            val collectionDetailBean = getItem(position)
            holder.bind(CollectSource(collectionDetailBean))
            holder.getView().setOnClickListener {
             callback(collectionDetailBean.url,collectionDetailBean.title,collectionDetailBean.cover)
            }
        }
    }

    override fun getItemCount(): Int {
        return mAsync.currentList.size
    }

    private fun getItem(position: Int): CollectionDetailBean {
        return mAsync.currentList[position]
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CollectionDetailBean>() {
            override fun areItemsTheSame(oldItem: CollectionDetailBean, newItem: CollectionDetailBean): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CollectionDetailBean, newItem: CollectionDetailBean): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }
}