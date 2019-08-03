package com.example.dailyinform.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dailyinform.fragment.CollectFragment
import com.example.dailyinform.fragment.InformationFragment
import com.example.dailyinform.R
import com.example.dailyinform.databinding.ActivityMainBinding
import com.example.dailyinform.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var binding: ActivityMainBinding
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canSlideFinish(false)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        initTableLayout()
        initViewPager()
    }

    private fun init() {
        viewPager = binding.mainViewpager
        tabLayout = binding.mainTbLayout
    }

    private fun initViewPager() {
        viewPager.adapter = PagerAdapter(fragmentList, supportFragmentManager!!)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })
    }

    private fun initTableLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("首页"))
        tabLayout.addTab(tabLayout.newTab().setText("收藏"))
        fragmentList.add(InformationFragment())
        fragmentList.add(CollectFragment())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}

