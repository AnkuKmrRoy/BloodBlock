package com.encora.bloodblock.ui.bloodbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.ActivityBloodBankBinding
import com.encora.bloodblock.ui.hospital.HospitalViewModel
import com.encora.bloodblock.ui.login.LoginActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.leopold.mvvm.ui.BindingActivity
import kotlinx.android.synthetic.main.activity_blood_bank.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.json.JSONException
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class BloodBankActivity : BindingActivity<ActivityBloodBankBinding>(), View.OnClickListener {

    private var qrScan: IntentIntegrator? = null
    override fun getLayoutResId() = R.layout.activity_blood_bank
    private val bloodBankViewModel: BloodBankViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bloodBankViewModel = bloodBankViewModel
        binding.setLifecycleOwner(this)
        qrScan =  IntentIntegrator(this)
        btScanQR.setOnClickListener(this)
        ivLogout.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v!!.getId()){
            R.id.btScanQR->{
                if(edtPatientID.getText().toString().equals("")){
                  edtPatientID.setError("Enter patient ID first")
                }else {
                    qrScan?.initiateScan()
                }
            }
            R.id.ivLogout->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    if(!edtPatientID.getText().toString().equals("") && edtPatientID.getText().toString().length > 0) {
                        bloodBankViewModel.saveBloodDetails(
                            edtPatientID.getText().toString(),
                            result.getContents()
                        )
                    }else{
                        edtPatientID.setError("Enter patient ID")
                    }
                } catch (e: Exception) {
                    e.printStackTrace();

                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}