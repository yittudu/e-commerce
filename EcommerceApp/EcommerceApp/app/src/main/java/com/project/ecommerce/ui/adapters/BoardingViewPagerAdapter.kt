package com.project.ecommerce.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class BoardingViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var fragmentList:List<Fragment>): FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}