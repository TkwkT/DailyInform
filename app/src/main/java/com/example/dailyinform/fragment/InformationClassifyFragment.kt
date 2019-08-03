package com.example.dailyinform.fragment


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.dailyinform.R
import com.example.dailyinform.adapter.InformationClassifyAdapter
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.databinding.FragmentInformationClassifyBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.viewmodel.InformationClassifyViewModel
import com.example.dailyinform.adapter.PagerAdapter

class InformationClassifyFragment : Fragment() {
    private lateinit var viewModel: InformationClassifyViewModel
    private lateinit var binding: FragmentInformationClassifyBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InformationClassifyAdapter
    private lateinit var viewPager: ViewPager
    private val detailFragmentList = ArrayList<InformationDetailFragment>()
    private var type = ""

    private val recyclerCallback: (position: Int) -> Unit = {
        setCurrentPosition(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString("type") ?: "wow"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information_classify, container, false)

        init()
        initRecyclerView()
        return binding.root
    }

    private fun init() {
        val factory = InjectorUtils.provideInformationClassifyViewModelFactory(requireContext())
        viewModel = ViewModelProviders.of(this, factory).get(InformationClassifyViewModel::class.java)
        viewModel.getData(type)

        recyclerView = binding.classifyRecycler
        viewPager = binding.classifyPager
    }

    private fun initRecyclerView() {
        adapter = InformationClassifyAdapter(recyclerCallback)
        recyclerView.adapter = adapter

        viewModel.classifyList.observe(viewLifecycleOwner, Observer {
            adapter.freshClassifyList(it)
            initViewPager(it)
        })
    }

    private fun initViewPager(list: List<ClassifyBean>) {
        for (i: Int in 0 until list.size) {
            detailFragmentList.add(InformationDetailFragment.newInstance(list[i].id))
        }

        viewPager.adapter = PagerAdapter(detailFragmentList, childFragmentManager)
        viewPager.currentItem = 0
        setViewPagerListener()
    }

    private fun setCurrentPosition(position: Int) {
        viewPager.currentItem = position
    }

    private fun setViewPagerListener() {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                adapter.setCurrentPosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
    }

    companion object {
        fun newInstance(type: String): InformationClassifyFragment {
            val classifyFragment = InformationClassifyFragment()
            val args = Bundle()
            args.putString("type", type)
            classifyFragment.arguments = args
            return classifyFragment
        }
    }

}
