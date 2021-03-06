package com.encora.bloodblock.ui.hospital.fragments.all_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.FragmentNotificationsBinding
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.encora.mytabapplication.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.leopold.mvvm.ui.BindingFragment
import kotlinx.android.synthetic.main.fragment_notifications.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRequestFragment : BindingFragment<FragmentNotificationsBinding>() {

    private val hospitalViewModel: HospitalViewModel by viewModel()

    override fun getLayoutResId() = R.layout.fragment_notifications

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.hospitalViewModel = hospitalViewModel
        binding.setLifecycleOwner(this)

        val sectionsPagerAdapter = context?.let { SectionsPagerAdapter(it, childFragmentManager) }
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        tabs.bringToFront()
    }


}