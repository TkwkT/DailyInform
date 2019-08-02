//package com.example.dailyinform.listener
//
//import android.content.Context
//import android.util.Log
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dailyinform.adapter.InformationDetailAdapter
//import com.example.dailyinform.bean.DetailBean
//import com.example.dailyinform.holder.BaseHolder
//import com.example.dailyinform.utils.InjectorUtils
//
//class InformationDetailScrollListener(
//    private val adapter: RecyclerView.Adapter<BaseHolder>,
//    private val classify: String,
//    private val context: Context,
//    private var callback: (detailList: List<DetailBean>) -> Unit
//) : RecyclerView.OnScrollListener() {
//
//    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//        super.onScrollStateChanged(recyclerView, newState)
//    }
//
//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        val layoutManager = recyclerView.layoutManager
//        if (layoutManager is LinearLayoutManager) {
//            val mLastChildPosition = layoutManager.findLastVisibleItemPosition()//当前页面最后一个可见的item的位置
//            val itemTotalCount = layoutManager.itemCount//获取总的item的数量
//            if (mLastChildPosition == itemTotalCount - 1) {//当前页面的最后一个item是item全部的最后一个并且当前页面的最后一个item的底部是recycleView的底部的时候，获取新数据
//                if (adapter is InformationDetailAdapter) {
//                    InjectorUtils.getDetailRepository(context).getDetail(classify, adapter.getPage()) {
//                        adapter.setPage()
//                        Log.d("aaa", classify)
//                        Log.d("aaa", it.toString())
//                        Log.d("aaa", adapter.getPage().toString())
//                        callback(it)
//                    }
//                }
//            }
//        }
//    }
//}