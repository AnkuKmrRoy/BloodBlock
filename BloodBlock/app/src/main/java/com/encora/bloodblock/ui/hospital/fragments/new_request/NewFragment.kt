package com.encora.bloodblock.ui.hospital.fragments.new_request

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.FragmentHomeBinding
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.leopold.mvvm.ui.BindingFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NewFragment : BindingFragment<FragmentHomeBinding>(),View.OnClickListener {
    private var rootView: View? = null
    var gender:String?=null
    private val hospitalViewModel: HospitalViewModel by viewModel()

    override fun getLayoutResId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.hospitalViewModel = hospitalViewModel
        binding.setLifecycleOwner(this)

        btSaveRequest.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        when(v!!.getId()){
            R.id.btSaveRequest -> {

                if(rbMale.isChecked){
                    gender =  rbMale.getText().toString()
                }else if(rbFemale.isChecked){
                    gender = rbFemale.getText().toString()
                }else{
                    gender=""
                }


                if (isValid()) {

                    hospitalViewModel.saveRequestData(
                        generatePatientUniqueID(
                            edtHospitalName.getText().toString(),
                            edtPatientName.getText().toString()
                        ),
                        edtHospitalName.getText().toString(),
                        edtPatientName.getText().toString(),
                        edtPatientMobile.getText().toString(),
                        edtFatherName.getText().toString(),
                        edtAddress.getText().toString(),
                        edtDisease.getText().toString(),
                        spBloodGroup.getSelectedItem().toString(),
                        gender!!,
                        edtRemark.getText().toString(),
                        false,
                        null
                    )

                    edtHospitalName.setText("")
                    edtPatientName.setText("")
                    edtPatientMobile.setText("")
                    edtFatherName.setText("")
                    edtAddress.setText("")
                    edtDisease.setText("")
                    spBloodGroup.setSelection(0)
                    rbMale.setChecked(false)
                    rbFemale.setChecked(false)
                    edtRemark.setText("")
                }
            }
        }
    }

    fun isValid():Boolean{
        var isValidInput = true

        if(edtHospitalName.getText().toString().equals("") && edtHospitalName.getText().toString().length<5){
            edtHospitalName.setError("Enter valid hospital name.")
            isValidInput = false
        }else if(edtPatientName.getText().toString().equals("") && edtPatientName.getText().toString().length<5){
            edtHospitalName.setError("Enter valid patient name.")
            isValidInput = false
        }else if(edtPatientMobile.getText().toString().equals("") && edtPatientMobile.getText().toString().length<9){
            edtHospitalName.setError("Enter valid mobile no.")
            isValidInput = false
        }else if(edtFatherName.getText().toString().equals("") && edtMobileNo.getText().toString().length<5){
            edtHospitalName.setError("Enter valid mobile no.")
            isValidInput = false
        }else if(edtAddress.getText().toString().equals("") && edtAddress.getText().toString().length>100){
            edtHospitalName.setError("Enter valid address.")
            isValidInput = false
        }else if(edtDisease.getText().toString().equals("") && edtDisease.getText().toString().length>100){
            edtHospitalName.setError("Enter valid Disease.")
            isValidInput = false
        }else if(spBloodGroup.selectedItemPosition == 0){
            Toast.makeText(context, "Select Blood Group", Toast.LENGTH_SHORT).show()
            isValidInput = false
        }else if(gender == null  || gender.equals("")){
            Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show()
            isValidInput = false
        }else if(edtRemark.getText().toString().equals("") && edtRemark.getText().toString().length>100){
            edtHospitalName.setError("Enter valid Disease.")
            isValidInput = false
        }

        return isValidInput
    }



    fun generatePatientUniqueID(hosName: String, patientName: String):String{
        var patientUniqueID:String = hosName.substring(0, 4) + patientName.substring(0, 4) + UUID.randomUUID().toString()
        return patientUniqueID
    }

}