package com.example.dailyinform.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dailyinform.R
import com.example.dailyinform.constdata.itemList
import com.example.dailyinform.constdata.itemNameList
import com.example.dailyinform.databinding.FragmentInformationBinding
import com.example.dailyinform.utils.DisplayUtils
import com.example.dailyinform.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout

class InformationFragment : Fragment() {
    private lateinit var tableLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentInformationBinding
    private val classifyFragmentList = ArrayList<InformationClassifyFragment>()
    private var curItemPosition = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        init()
        initViewPager()
        initTabLayout()
        return binding.root
    }

    private fun init() {
        viewPager = binding.informationViewpager
        tableLayout = binding.tlItem
    }

    private fun initViewPager() {
        for (i: Int in 0 until itemList.size) {
            classifyFragmentList.add(InformationClassifyFragment.newInstance(itemNameList[i]))
        }
        viewPager.adapter = PagerAdapter(classifyFragmentList, fragmentManager!!)
        viewPager.currentItem = curItemPosition

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                tableLayout.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })

    }

    private fun initTabLayout() {
        for (str: String in itemList) {
            tableLayout.addTab(tableLayout.newTab().setText(str))
        }

        val linearLayout = tableLayout.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.tablayout_divider_vertical)
        linearLayout.dividerPadding = DisplayUtils.dip2px(requireContext(), 16f)

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
