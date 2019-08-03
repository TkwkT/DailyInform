package com.example.dailyinform.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dailyinform.constdata.itemNameList


class PagerAdapter(private val fragmentList: List<Fragment>,fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return itemNameList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {

    }
}