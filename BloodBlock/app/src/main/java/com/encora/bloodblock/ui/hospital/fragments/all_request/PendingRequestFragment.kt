package com.encora.bloodblock.ui.hospital.fragments.all_request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.FragmentCompleatedRequestBinding
import com.encora.bloodblock.databinding.FragmentPendingRequestBinding
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.leopold.mvvm.ui.BindingFragment
import kotlinx.android.synthetic.main.fragment_compleated_request.*
import kotlinx.android.synthetic.main.fragment_pending_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PendingRequestFragment : BindingFragment<FragmentPendingRequestBinding>() {

    override fun getLayoutResId() = R.layout.fragment_pending_request

    private val hospitalViewModel: HospitalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hospitalViewModel = hospitalViewModel
        binding.setLifecycleOwner(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isVisible)
            initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPendingRequests.layoutManager = LinearLayoutManager(context)
        getCompleatedRequestList()
    }


    fun getCompleatedRequestList() {
        hospitalViewModel.getRequestList(false).observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                tvNoDataFoundPending.visibility = View.GONE
                binding.rvPendingRequests.visibility = View.VISIBLE
                binding.rvPendingRequests.adapter = PendingRequestRecyclerViewAdapter(it)

            } else {
                binding.rvPendingRequests.visibility = View.GONE
                tvNoDataFoundPending.visibility = View.VISIBLE
            }

        })
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PendingRequestFragment {
            return PendingRequestFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}