package com.example.test2.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.test2.R
import com.example.test2.adapter.HomeAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        val v: View = inflater.inflate(R.layout.fragment_home, container, false)

        tabLayout = v.findViewById(R.id.tabLayout)
        viewPager = v.findViewById(R.id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Chat"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Status"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Call"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = HomeAdapter(requireContext(), childFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return v
    }
}