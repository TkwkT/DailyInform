package com.example.dailyinform


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyinform.adapter.InformationDetailAdapter
import com.example.dailyinform.databinding.FragmentInformationDetailBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.viewmodel.InformationDetailViewModel
import androidx.lifecycle.Observer
import com.example.dailyinform.listener.InformationDetailScrollListener

class InformationDetailFragment : Fragment() {
    private lateinit var viewModel: InformationDetailViewModel
    private lateinit var binding: FragmentInformationDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InformationDetailAdapter
    private var classify = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        classify = arguments?.getString("classify") ?: "mogu"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_detail, container, false)

        init()
        setData()
        return binding.root
    }

    private fun init(){
        val factory = InjectorUtils.provideInformationDetailViewModelFactory(requireContext())
        viewModel = ViewModelProviders.of(this, factory).get(InformationDetailViewModel::class.java)
        viewModel.getData(classify,1)
        recyclerView = binding.detailRecycler
        adapter = InformationDetailAdapter{ url: String, title: String ->
            changeActivity(url,title)
        }
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(InformationDetailScrollListener(adapter,classify,requireContext()){
            adapter.freshData(it)
        })
    }

    private fun changeActivity(url: String, title: String){
        val intent = Intent(requireContext(),DetailActivity::class.java)
        intent.putExtra("detail_url",url)
        intent.putExtra("detail_title",title)
        startActivity(intent)
    }

    private fun setData(){
        viewModel.detailList.observe(viewLifecycleOwner, Observer {
            adapter.freshData(it)
        })
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
