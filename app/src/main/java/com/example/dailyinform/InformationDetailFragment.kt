package com.example.dailyinform


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.adapter.InformationDetailPagingAdapter
import com.example.dailyinform.databinding.FragmentInformationDetailBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.viewmodel.InformationDetailViewModel

class InformationDetailFragment : Fragment() {
    private lateinit var viewModel: InformationDetailViewModel
    private lateinit var binding: FragmentInformationDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InformationDetailPagingAdapter
    private var classify = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        classify = arguments?.getString("classify") ?: "mogu"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_detail, container, false)

        init()
        return binding.root
    }

    private fun init() {
        val factory = InjectorUtils.provideInformationDetailViewModelFactory(requireContext())
        viewModel = ViewModelProviders.of(this, factory).get(InformationDetailViewModel::class.java)
        initAdapter()
        recyclerView = binding.detailRecycler
        recyclerView.adapter = adapter
//        recyclerView.addOnScrollListener(InformationDetailScrollListener(adapter,classify,requireContext()){
//            adapter.freshData(it)
//        })
    }

    private fun initAdapter() {
        adapter = InformationDetailPagingAdapter { url: String, title: String ->
            changeActivity(url, title)
        }
        viewModel.getData(classify) {
            it.observe(this, Observer { list ->
                if (list != null) {
                    Log.d("aaa", list.toString())
                    adapter.submitList(list)
                }
            })
        }
    }

    private fun changeActivity(url: String, title: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("detail_url", url)
        intent.putExtra("detail_title", title)
        startActivity(intent)
    }

    companion object {
        fun newInstance(classify: String): InformationDetailFragment {
            val detailFragment = InformationDetailFragment()
            val args = Bundle()
            args.putString("classify", classify)
            detailFragment.arguments = args
            return detailFragment
        }
    }
}
