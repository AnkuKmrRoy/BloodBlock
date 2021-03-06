package com.encora.bloodblock.ui.patient

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.encora.bloodblock.R
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.databinding.ActivityPatientBinding
import com.encora.bloodblock.ui.login.LoginActivity
import com.leopold.mvvm.ui.BindingActivity
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PatientActivity : BindingActivity<ActivityPatientBinding>(),View.OnClickListener {

    override fun getLayoutResId() = R.layout.activity_patient

    private val patientViewModel: PatientViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.patientViewModel = patientViewModel
        binding.setLifecycleOwner(this)
        ivLogout.setOnClickListener(this)

        getPatientID()
    }

    fun getPatientID(){

        patientViewModel.getPatientDetails("5555555555").observe(this, Observer {
            if (it != null){
                showPatientID(it)
            }
        })
    }

    fun showPatientID(patientDetails:HospitalData){
        tvDispPatientID.setText(patientDetails.patientID)
        tvDispHospitalName.setText(patientDetails.hospitalName)
        tvDispPatientName.setText(patientDetails.patientName)
        tvDispPatientMobile.setText(patientDetails.patientMobile)
        tvDispFatherName.setText(patientDetails.fatherName)
        tvDispAddress.setText(patientDetails.address)
        tvDispDisease.setText(patientDetails.disease)
        tvDispBloodGroup.setText(patientDetails.needed_blood_group)
        tvDispGender.setText(patientDetails.gender)
        tvDispRemark.setText(patientDetails.remark)
    }

    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.ivLogout->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}