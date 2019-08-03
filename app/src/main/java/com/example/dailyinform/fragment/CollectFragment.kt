package com.example.dailyinform.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.R
import com.example.dailyinform.activity.DetailActivity
import com.example.dailyinform.adapter.CollectAdapter
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.databinding.FragmentCollectBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.viewmodel.CollectionDetailViewModel

class CollectFragment : Fragment() {
    private lateinit var binding: FragmentCollectBinding
    private lateinit var viewModel: CollectionDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CollectAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collect, container, false)

        init()
        initRecyclerView()

        return binding.root
    }

    private fun init() {
        val factory = InjectorUtils.provideCollectionDetailViewModelFactory(requireContext())
        viewModel = ViewModelProviders.of(this, factory).get(CollectionDetailViewModel::class.java)
        viewModel.getCollectionDetailBeanList()
        recyclerView = binding.collectRecycler

    }

    private fun initRecyclerView() {
        adapter = CollectAdapter { url: String, title: String, cover: String? ->
            changeActivity(url,title,cover)
        }
        recyclerView.adapter = adapter
        viewModel.hasData
        viewModel.collectionDetailList.observe(this, Observer {
            adapter.submitData(it)
        })
        viewModel.hasData.observe(this, Observer {
            if (it) {
                binding.collectRecycler.visibility = View.VISIBLE
                binding.collectTv.visibility = View.GONE

            } else {
                binding.collectRecycler.visibility = View.GONE
                binding.collectTv.visibility = View.VISIBLE
            }
        })
    }
    private fun changeActivity(url: String, title: String,cover:String?) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("detail_url", url)
        intent.putExtra("detail_title", title)
        intent.putExtra("detail_cover",cover)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCollectionDetailBeanList()
    }
}
