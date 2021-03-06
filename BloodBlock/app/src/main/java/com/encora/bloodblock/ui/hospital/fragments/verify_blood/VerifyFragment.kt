package com.encora.bloodblock.ui.hospital.fragments.verify_blood

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.encora.bloodblock.R
import com.encora.bloodblock.data.db.entity.HospitalData
import com.encora.bloodblock.databinding.FragmentDashboardBinding
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.encora.bloodblock.ui.hospital.VerifyScannedQRCode
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE
import com.leopold.mvvm.ui.BindingFragment
import kotlinx.android.synthetic.main.activity_blood_bank.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.btScanQR
import kotlinx.android.synthetic.main.fragment_dashboard.edtPatientID
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


class VerifyFragment : BindingFragment<FragmentDashboardBinding>() ,View.OnClickListener{

    private val hospitalViewModel: HospitalViewModel by activityViewModels()
    private var qrScan: IntentIntegrator? = null
    override fun getLayoutResId() = R.layout.fragment_dashboard
    lateinit var hospitalData:HospitalData

    private var verifyScannedQRCode:VerifyScannedQRCode? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //verifyScannedQRCode = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.hospitalViewModel = hospitalViewModel
        binding.setLifecycleOwner(this)
        if(isVisible) {
            getPatientID()
        }
        qrScan =  IntentIntegrator(activity)
        btScanQR.setOnClickListener(this)
    }

    fun getPatientID(){
        hospitalViewModel.getPatientUniqueID("5555555555").observe(viewLifecycleOwner, Observer {
            if(it != null) {
                showPatientID(it.patientID)
                hospitalData = it
            }
        })
    }

    fun showPatientID(patientID: String){
        edtPatientID.setText(patientID)
    }

    override fun onClick(v: View?) {
        when(v!!.getId()){
            R.id.btScanQR->{
                qrScan?.initiateScan();
            }
        }
        scannedQRCode()
    }






     fun scannedQRCode(){
            var qrCode:String

            hospitalViewModel.scannedQRCode.observe(viewLifecycleOwner,
                Observer {
                    qrCode = it

                    try {
                        hospitalViewModel.getQRCodeByPatientID(edtPatientID.getText().toString()).observe(viewLifecycleOwner,
                            Observer {
                                if(it != null && it.qr_code.equals(qrCode)) {
                                    verifyQRCode(it.qr_code)
                                } else
                                    Toast.makeText(activity,"Verificaiton failed",Toast.LENGTH_SHORT).show()
                            })

                    } catch (e: Exception) {
                        e.printStackTrace();
                        //if control comes here
                        //that means the encoded format not matches
                        //in this case you can display whatever data is available on the qrcode
                        //to a toast
                        Toast.makeText(activity, qrCode, Toast.LENGTH_LONG).show();
                    }
                })




    }

    fun verifyQRCode(qrCode:String){
        hospitalData = HospitalData(hospitalData.patientID,hospitalData.hospitalName,hospitalData.patientName,
            hospitalData.patientMobile,hospitalData.fatherName,hospitalData.address,hospitalData.disease,
            hospitalData.needed_blood_group,hospitalData.gender,hospitalData.remark,true,qrCode)

        hospitalViewModel.updateHospitalData(hospitalData)

        Toast.makeText(activity,"Verified Successfully",Toast.LENGTH_SHORT).show()

    }

    interface VerifyScannedQRCode {
        fun scannedQRCode(qrCode:String)
    }

}