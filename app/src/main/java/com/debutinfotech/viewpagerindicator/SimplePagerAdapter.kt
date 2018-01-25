package com.debutinfotech.viewpagerindicator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SimplePagerAdapter(fm: FragmentManager, private val mTutorialsList: List<Item>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PagerItem.newInstance(mTutorialsList[position])

    }

    override fun getCount(): Int {
        return mTutorialsList.size
    }
}