package com.muhammadusama.holyquran.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.muhammadusama.holyquran.fragments.*


class ViewPagerAdapter(fragmentActivity: HomeFragment) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4;
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SurahFragment()
            1 -> ParaFragment()
            2 -> PageFragment()
            3 -> HijbFragment()
            else -> SurahFragment()
        }
    }

}