package com.encora.mytabapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.encora.bloodblock.R
import com.encora.bloodblock.ui.hospital.fragments.all_request.CompleatedRequestFragment
import com.encora.bloodblock.ui.hospital.fragments.all_request.PendingRequestFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return PendingRequestFragment.newInstance(position+1)
            }
            1->{
                return CompleatedRequestFragment.newInstance(position+1)
            }
        }
        return PendingRequestFragment.newInstance(position+1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}