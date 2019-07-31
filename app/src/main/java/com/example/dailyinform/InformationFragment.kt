package com.example.dailyinform


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.dailyinform.constdata.itemList
import com.example.dailyinform.constdata.itemNameList
import com.example.dailyinform.databinding.FragmentInformationBinding
import com.example.dailyinform.utils.InjectorUtils
import com.example.dailyinform.utils.viewModels
import com.example.dailyinform.viewmodel.InformationClassifyViewModel
import com.example.dailyinform.viewpager.PagerAdapter
import com.google.android.material.tabs.TabLayout

class InformationFragment : Fragment() {
    private var curItemPosition = 0
    private lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentInformationBinding
    private val classifyFragmentList = ArrayList<InformationClassifyFragment>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)

        init()
        initTableLayout()
        return binding.root
    }

    private fun init() {
        viewPager = binding.informationViewpager
        for (i: Int in 0 until itemList.size) {
            classifyFragmentList.add(InformationClassifyFragment.newInstance(itemNameList[i]))
        }
        viewPager.adapter = PagerAdapter(classifyFragmentList, fragmentManager!!)
        viewPager.currentItem = 0
    }

    private fun initTableLayout() {
        val tableLayout = binding.tlItem
        for (str: String in itemList) {
            tableLayout.addTab(tableLayout.newTab().setText(str))
        }
        tableLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                curItemPosition = tab!!.position
                viewPager.currentItem = curItemPosition
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }


}
