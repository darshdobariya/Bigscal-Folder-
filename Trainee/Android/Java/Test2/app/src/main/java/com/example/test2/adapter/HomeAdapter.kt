package com.example.test2.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.test2.tabs.CallFragment
import com.example.test2.tabs.ChatFragment
import com.example.test2.tabs.StatusFragment

class HomeAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val ChatFragment: ChatFragment = ChatFragment()
                return ChatFragment()
            }
            1 -> {
                return StatusFragment()
            }
            else -> {
                // val movieFragment = MovieFragment()
                return CallFragment()
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}