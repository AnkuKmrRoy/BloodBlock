package com.encora.bloodblock.ui.hospital

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.encora.bloodblock.R
import com.encora.bloodblock.databinding.ActivityHospitalBinding
import com.encora.bloodblock.ui.login.LoginActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.leopold.mvvm.ui.BindingActivity
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.custom_toolbar.*

import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class HospitalActivity : BindingActivity<ActivityHospitalBinding>() ,VerifyScannedQRCode, View.OnClickListener{
    private val hospitalViewModel: HospitalViewModel by viewModel()
    override fun getLayoutResId() = R.layout.activity_hospital
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.hospitalViewModel = hospitalViewModel
        binding.setLifecycleOwner(this)
        ivLogout.setOnClickListener(this)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //verifyScannedQRCode = (this as? VerifyScannedQRCode)!!

    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    hospitalViewModel.scannedQRCode.postValue(result.getContents())
                    //verifyScannedQRCode?.scannedQRCode(result.getContents())
                    //hospitalViewModel.scannedQRCode(result.getContents())
                } catch (e: Exception) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun scannedQRCode(patientUniqueID: String) {

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