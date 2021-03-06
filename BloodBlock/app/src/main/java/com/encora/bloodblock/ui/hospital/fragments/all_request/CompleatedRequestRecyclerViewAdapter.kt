package com.encora.bloodblock.ui.hospital.fragments.all_request

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.encora.bloodblock.R
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.databinding.RequestListRowItemBinding
import java.lang.Exception

class CompleatedRequestRecyclerViewAdapter (private val productList: List<HospitalData>) : RecyclerView.Adapter<ProductListRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListRecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RequestListRowItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.request_list_row_item, parent, false)
        return ProductListRecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListRecyclerViewHolder, position: Int) {
        holder.bind(productList[position])
    }


}

class ProductListRecyclerViewHolder(val binding: RequestListRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(requestData: HospitalData) {
        binding.tvPatientName.text = requestData.patientName
        binding.tvPatientUniqueID.text = requestData.patientID
        binding.tvPatientHospitalName.text = requestData.hospitalName
        binding.tvPatientMobileNo.text = requestData.patientMobile
    }

}


